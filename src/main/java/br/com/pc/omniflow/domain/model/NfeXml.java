package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NFE_XML")
public class NfeXml {

    @PrePersist
    protected void onCreate() {
        if (this.dataImportacao == null) {
            this.dataImportacao = LocalDateTime.now();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NFE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "NFE_CHAVE_ACESSO", length = 44, nullable = false, unique = true)
    private String chaveAcesso;

    @Column(name = "NFE_XML_ORIGINAL", nullable = false, columnDefinition = "TEXT")
    private String xmlOriginal;

    @Column(name = "NFE_DATA_IMPORTACAO", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataImportacao;

    @Enumerated(EnumType.STRING) // Aqui a mágica para salvar a String do Enum
    @Column(name = "NF_STATUS_PROCESSAMENTO", length = 20, nullable = false,
            columnDefinition = "VARCHAR(20) DEFAULT 'RECEBIDO'")
    private StatusProcessamento statusProcessamento = StatusProcessamento.RECEBIDO;

    @Column(name = "LOG_ERRO", length = 200)
    private String logErro;

    public NfeXml() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getXmlOriginal() {
        return xmlOriginal;
    }

    public void setXmlOriginal(String xmlOriginal) {
        this.xmlOriginal = xmlOriginal;
    }

    public LocalDateTime getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDateTime dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public StatusProcessamento getStatusProcessamento() {
        return statusProcessamento;
    }

    public void setStatusProcessamento(StatusProcessamento statusProcessamento) {
        this.statusProcessamento = statusProcessamento;
    }

    public String getLogErro() {
        return logErro;
    }

    public void setLogErro(String logErro) {
        this.logErro = logErro;
    }
}