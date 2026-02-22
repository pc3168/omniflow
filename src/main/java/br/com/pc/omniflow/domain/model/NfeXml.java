package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NFE_XML")
public class NfeXml {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NFE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "NFE_CHAVE_ACESSO", length = 44, nullable = false)
    private String chaveAcesso;

    @Lob
    @Column(name = "NFE_XML_ORIGINAL", nullable = false)
    private byte[] xmlOriginal;

    @Column(name = "NFE_DATA_IMPORTACAO", nullable = false)
    private LocalDateTime dataImportacao;

    @Column(name = "NF_STATUS_PROCESSAMENTO", length = 20)
    private String statusProcessamento;

    @Column(name = "LOG_ERRO", length = 200)
    private String logErro;

    public NfeXml() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GrupoEmpresa getGrupo() { return grupo; }
    public void setGrupo(GrupoEmpresa grupo) { this.grupo = grupo; }

    public String getChaveAcesso() { return chaveAcesso; }
    public void setChaveAcesso(String chaveAcesso) { this.chaveAcesso = chaveAcesso; }

    public byte[] getXmlOriginal() { return xmlOriginal; }
    public void setXmlOriginal(byte[] xmlOriginal) { this.xmlOriginal = xmlOriginal; }

    public LocalDateTime getDataImportacao() { return dataImportacao; }
    public void setDataImportacao(LocalDateTime dataImportacao) { this.dataImportacao = dataImportacao; }

    public String getStatusProcessamento() { return statusProcessamento; }
    public void setStatusProcessamento(String statusProcessamento) { this.statusProcessamento = statusProcessamento; }

    public String getLogErro() { return logErro; }
    public void setLogErro(String logErro) { this.logErro = logErro; }
}