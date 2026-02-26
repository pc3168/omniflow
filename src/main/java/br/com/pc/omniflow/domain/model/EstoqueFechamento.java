package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ESTOQUE_FECHAMENTOS")
public class EstoqueFechamento extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEC_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ENT_LOCAL", nullable = false)
    private Entidade local; // Onde está

    @ManyToOne
    @JoinColumn(name = "ENT_PROPRIETARIO", nullable = false)
    private Entidade proprietario; // De quem é

    @Column(name = "FEC_MES_REFERENCIA", nullable = false)
    private Integer mes;

    @Column(name = "FEC_ANO_REFERENCIA", nullable = false)
    private Integer ano;

    @Column(name = "FEC_QUANTIDADE_FINAL", precision = 15, scale = 4, nullable = false)
    private BigDecimal quantidadeFinal;

    @Column(name = "FEC_LOTE", length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'ND'")
    private String lote = "ND";

    @Column(name = "FEC_VALIDADE")
    private LocalDate validade;

    public EstoqueFechamento() {}

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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entidade getLocal() {
        return local;
    }

    public void setLocal(Entidade local) {
        this.local = local;
    }

    public Entidade getProprietario() {
        return proprietario;
    }

    public void setProprietario(Entidade proprietario) {
        this.proprietario = proprietario;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getQuantidadeFinal() {
        return quantidadeFinal;
    }

    public void setQuantidadeFinal(BigDecimal quantidadeFinal) {
        this.quantidadeFinal = quantidadeFinal;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueFechamento that = (EstoqueFechamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
