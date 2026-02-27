package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.model.NfeXml;
import br.com.pc.omniflow.domain.repository.NfeXmlRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        importar(gruId, arquivo, arquivo.getName());
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
                    processarXmlUnico(arquivo, nomeOriginal);
                } else {
                    log.info(this.getClass(), "Formato de arquivo não suportado: " + nomeOriginal);
                }

            } catch (Exception e) {
                log.erro(this.getClass(), "Erro crítico ao importar arquivo " + nomeOriginal, e);
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
                    salvarNoBanco(entry.getName(), conteudoXml);
                }
            }
        }
    }

    private void processarXmlUnico(File arquivo, String nomeArquivo) throws IOException {
        String conteudoXml = Files.readString(arquivo.toPath(), StandardCharsets.UTF_8);
        salvarNoBanco(nomeArquivo, conteudoXml);
    }

    private void salvarNoBanco(String nomeArquivo, String conteudoXml) {
        try {
            // 1. Extrai a chave de acesso via Regex rápido (sem parser pesado ainda)
            String chaveAcesso = extrairChaveAcessoSimples(conteudoXml);

            // 2. Verifica se já existe (O Spring Data usará o filtro de grupo aqui)
            if (nfeXmlRepository.existsByChaveAcesso(chaveAcesso)) {
                log.info(this.getClass(), "XML ignorado (Chave já existe): " + chaveAcesso);
                return;
            }

            // 3. Persiste na staging area (NFE_XMLS)
            NfeXml nfeXml = new NfeXml();
            nfeXml.setChaveAcesso(chaveAcesso);
            nfeXml.setXmlOriginal(conteudoXml);
            nfeXml.setNomeArquivo(nomeArquivo);
            nfeXml.setStatusProcessamento(StatusProcessamento.RECEBIDO);

            nfeXmlRepository.save(nfeXml);
            log.info(this.getClass(), "XML salvo com sucesso: " + chaveAcesso);

        } catch (Exception e) {
            lancarErro("Falha ao salvar XML no banco: " + nomeArquivo, e);
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
}
