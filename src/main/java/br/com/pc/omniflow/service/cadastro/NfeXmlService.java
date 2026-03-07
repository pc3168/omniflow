package br.com.pc.omniflow.service.cadastro;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.enums.TipoXml;
import br.com.pc.omniflow.domain.model.NfeXml;
import br.com.pc.omniflow.domain.repository.NfeXmlRepository;
import br.com.pc.omniflow.service.BaseService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class NfeXmlService extends BaseService {

    private final NfeXmlRepository nfeXmlRepository;

    public NfeXmlService(NfeXmlRepository nfeXmlRepository) {
        this.nfeXmlRepository = nfeXmlRepository;
    }


    public void importar(Long gruId, File arquivo){
        if (arquivo.isDirectory()){
            for (File arFile : arquivo.listFiles()){
                importar(gruId, arFile, arFile.getName());
            }
        }else{
            importar(gruId, arquivo, arquivo.getName());
        }
    }

    /**
     * Ponto de entrada principal. Decide se é ZIP ou XML e processa.
     */
    public void importar(Long gruId, File arquivo, String nomeOriginal) {
        comFiltro(gruId, () -> {
            try {
                log.info(this.getClass(), "Recebido arquivo para importação: " + nomeOriginal);

                if (nomeOriginal.toLowerCase().endsWith(".zip")) {
                    processarZip(gruId, arquivo);
                } else if (nomeOriginal.toLowerCase().endsWith(".xml")) {
                    processarXmlUnico(gruId, arquivo, nomeOriginal);
                } else {
                    log.info(this.getClass(), "Formato de arquivo não suportado: " + nomeOriginal);
                }

            } catch (Exception e) {
                lancarErro("Erro crítico ao importar arquivo " + nomeOriginal, e);
            }
            return null;
        });
    }

    private void processarZip(Long gruId, File arquivoZip) throws IOException {
        log.info(this.getClass(), "Descompactando arquivo ZIP...");

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivoZip))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                // Filtra apenas arquivos .xml (ignora pastas e outros arquivos)
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
                    log.info(this.getClass(), "Extraindo e processando: " + entry.getName());

                    // Lê o conteúdo do XML de dentro do ZIP
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }

                    String conteudoXml = out.toString(StandardCharsets.UTF_8);
                    salvarNoBanco(gruId, entry.getName(), conteudoXml);
                }
            }
//            moverParaProcessados(arquivoZip);
        }
    }

    private void processarXmlUnico(Long gruId, File arquivo, String nomeArquivo) throws IOException {
        String conteudoXml = Files.readString(arquivo.toPath(), StandardCharsets.UTF_8);
        salvarNoBanco(gruId, nomeArquivo, conteudoXml);
//        moverParaProcessados(arquivo);
    }

    private void moverParaProcessados(File arquivoOriginal) {
        try {
            Path pastaProcessados = Paths.get(arquivoOriginal.getParent(), "processados");

            if (Files.notExists(pastaProcessados)) {
                Files.createDirectories(pastaProcessados);
            }

            Path destino = pastaProcessados.resolve(arquivoOriginal.getName());

            // ATOMIC_MOVE: garante que o arquivo não fique "corrompido" no meio do caminho
            // REPLACE_EXISTING: se já existir um com o mesmo nome lá, ele sobrescreve
            Files.move(arquivoOriginal.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

            log.info(this.getClass(), "Arquivo movido para: " + destino);

        } catch (IOException e) {
            lancarErro("Falha ao mover arquivo: " + arquivoOriginal.getName(), e);
        }
    }

    private String extrairChaveAcessoSimples(String xml) {
        Pattern pattern = Pattern.compile("chNFe>(\\d{44})<|infNFe Id=\"NFe(\\d{44})\"");
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            return matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
        }
        return "CHAVE-NAO-IDENTIFICADA-" + System.currentTimeMillis();
    }


    private void salvarNoBanco(Long gruId, String nomeArquivo, String conteudoXml) {
        try {
            String chaveAcesso = extrairChaveAcessoSimples(conteudoXml);
            TipoXml tipo = TipoXml.identificar(conteudoXml);

            if (contemChave(gruId, chaveAcesso)) {
                log.info(this.getClass(), "XML ignorado (Chave já existe): " + chaveAcesso);
                return;
            }

            NfeXml nfeXml = new NfeXml();
            nfeXml.setChaveAcesso(chaveAcesso);
            nfeXml.setXmlOriginal(conteudoXml);
            nfeXml.setNomeArquivo(nomeArquivo);
            nfeXml.setTipoXml(tipo);
            nfeXml.setStatusProcessamento(StatusProcessamento.RECEBIDO);

            this.salvar(gruId, nfeXmlRepository, nfeXml);
            log.info(this.getClass(), "XML salvo com sucesso: " + chaveAcesso);

        } catch (Exception e) {
            lancarErro("Falha ao salvar XML no banco: " + nomeArquivo, e);
        }
    }

    public List<NfeXml> listarPorGrupo(Long gruId) {
        return comFiltro(gruId, nfeXmlRepository::findAll);
    }

    public Optional<NfeXml> buscarPorId(Long gruId, Long id) {
        return comFiltro(gruId, () -> nfeXmlRepository.findById(id));
    }

    public void excluir(Long gruId, Long id) {
        comFiltro(gruId, () -> {
            nfeXmlRepository.deleteById(id);
            return null;
        });
    }

    private boolean contemChave(Long gruId, String chaveAcesso){
        return comFiltro(gruId, () -> nfeXmlRepository.existsByChaveAcesso(chaveAcesso));
    }

}
