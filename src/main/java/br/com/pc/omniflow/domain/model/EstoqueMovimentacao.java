package br.com.pc.omniflow.domain.model;


import br.com.pc.omniflow.converter.OrigemMovimentoConverter;
import br.com.pc.omniflow.domain.enums.OrigemMovimento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ESTOQUE_MOVIMENTACAO")
public class EstoqueMovimentacao extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOV_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ENT_PROPRIETARIO", nullable = false)
    private Entidade proprietario; // O dono legal

    @ManyToOne
    @JoinColumn(name = "ENT_LOCAL", nullable = false)
    private Entidade local; // Onde está fisicamente

    @Column(name = "MOV_QUANTIDADE", nullable = false)
    private BigDecimal quantidade;

    @Column(name = "MOV_SINAL", length = 1, nullable = false)
    private String sinal; // + ou -

    @Column(name = "MOV_LOTE",length = 50, nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'ND'")
    private String lote = "ND";

    @Column(name = "MOV_DATA_MOVIMENTO", nullable = false)
    private LocalDateTime dataMovimento = LocalDateTime.now();

    @Convert(converter = OrigemMovimentoConverter.class)
    @Column(name = "MOV_ORIGEM", length = 2, nullable = false)
    private OrigemMovimento origem;

    @Column(name = "MOV_ORIGEM_ID", nullable = false)
    private Long origemId; // ID da NfeItem ou do EstoqueAjuste

    public EstoqueMovimentacao() {
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entidade getProprietario() {
        return proprietario;
    }

    public void setProprietario(Entidade proprietario) {
        this.proprietario = proprietario;
    }

    public Entidade getLocal() {
        return local;
    }

    public void setLocal(Entidade local) {
        this.local = local;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getSinal() {
        return sinal;
    }

    public void setSinal(String sinal) {
        this.sinal = sinal;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDateTime getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public OrigemMovimento getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemMovimento origem) {
        this.origem = origem;
    }

    public Long getOrigemId() {
        return origemId;
    }

    public void setOrigemId(Long origemId) {
        this.origemId = origemId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueMovimentacao that = (EstoqueMovimentacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
