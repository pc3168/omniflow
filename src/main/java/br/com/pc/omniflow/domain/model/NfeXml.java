package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.enums.TipoXml;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "NFE_XMLS")
public class NfeXml extends BaseEntity{

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

    @Column(name = "XML_NOME_ARQUIVO")
    private String nomeArquivo;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "NFE_TIPO_XML", length = 20)
    private TipoXml tipoXml;

    public NfeXml() {}

    public NfeXml(Long gruId) {
        this.grupo = new GrupoEmpresa(gruId);
    }

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

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public TipoXml getTipoXml() {
        return tipoXml;
    }

    public void setTipoXml(TipoXml tipoXml) {
        this.tipoXml = tipoXml;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NfeXml nfeXml = (NfeXml) o;
        return Objects.equals(id, nfeXml.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NfeXml{" +
                "id=" + id +
                ", grupo=" + grupo +
                ", chaveAcesso='" + chaveAcesso + '\'' +
                ", nomeArquivo='" + nomeArquivo + '\'' +
                ", xmlOriginal='" + xmlOriginal + '\'' +
                ", dataImportacao=" + dataImportacao +
                ", statusProcessamento=" + statusProcessamento +
                ", logErro='" + logErro + '\'' +
                ", tipoXml=" + tipoXml +
                '}';
    }
}