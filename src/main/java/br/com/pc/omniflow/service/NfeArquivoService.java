package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.model.NfeXml;
import br.com.pc.omniflow.domain.repository.NfeXmlRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class NfeArquivoService extends BaseService {

    private final NfeXmlRepository nfeXmlRepository;

    public NfeArquivoService(NfeXmlRepository nfeXmlRepository) {
        this.nfeXmlRepository = nfeXmlRepository;
    }

    /**
     * Processa um arquivo ZIP: Extrai para pasta local e inicia a persistência.
     */
    public void processarZip(Long gruId, InputStream zipInputStream, Path destinoPasta) throws IOException {
        // 1. Extrair os arquivos do ZIP para a pasta destino
        extrairZip(zipInputStream, destinoPasta);

        // 2. Ler os arquivos da pasta e salvar no banco usando seu helper comFiltro
        comFiltro(gruId, () -> {
            try {
                processarArquivosDaPasta(gruId, destinoPasta);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao ler pasta de extração", e);
            }
            return null;
        });
    }

    private void extrairZip(InputStream zipStream, Path targetDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".xml")) {
                    Path newPath = targetDir.resolve(entry.getName());
                    Files.createDirectories(newPath.getParent());
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    private void processarArquivosDaPasta(Long gruId, Path pasta) throws IOException {
        try (var stream = Files.walk(pasta)) {
            stream.filter(Files::isRegularFile)
                    .filter(file -> file.toString().toLowerCase().endsWith(".xml"))
                    .forEach(file -> {
                        try {
                            String conteudoXml = Files.readString(file);
                            String nomeArquivo = file.getFileName().toString();

                            // Aqui você precisaria de um Regex ou Parser rápido
                            // para pegar a chave de acesso antes do Parser completo
                            String chaveAcesso = extrairChaveAcessoSimples(conteudoXml);

                            if (!nfeXmlRepository.existsByChaveAcesso(chaveAcesso)) {
                                NfeXml nfeXml = new NfeXml();
                                nfeXml.setXmlOriginal(conteudoXml);
                                nfeXml.setNomeArquivo(nomeArquivo);
                                nfeXml.setChaveAcesso(chaveAcesso);
                                nfeXml.setStatusProcessamento(StatusProcessamento.RECEBIDO);
                                // O grupoId e Data serão preenchidos pela lógica do JPA/BaseEntity

                                nfeXmlRepository.save(nfeXml);

                                // Opcional: Mover para uma pasta "OK" ou deletar
                                Files.delete(file);
                            }
                        } catch (Exception e) {
                            System.err.println("Erro ao processar arquivo " + file + ": " + e.getMessage());
                        }
                    });
        }
    }

    // Um Regex simples para pegar a chave sem precisar dar parse no XML todo ainda
    private String extrairChaveAcessoSimples(String xml) {
        Pattern pattern = Pattern.compile("chNFe>(\\d{44})<");
        Matcher matcher = pattern.matcher(xml);
        return matcher.find() ? matcher.group(1) : "CHAVE-NAO-IDENTIFICADA-" + System.currentTimeMillis();
    }
}
