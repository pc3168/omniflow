package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.MotivoAjusteConverter;
import br.com.pc.omniflow.domain.enums.MotivoAjuste;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ESTOQUE_AJUSTES")
public class EstoqueAjuste extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AJU_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "ENT_LOCAL", nullable = false)
    private Entidade local; // Onde o ajuste foi feito

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @Convert(converter = MotivoAjusteConverter.class)
    @Column(name = "AJU_MOTIVO", length = 1, nullable = false)
    private MotivoAjuste motivo;

    @Column(name = "AJU_QUANTIDADE", precision = 15, scale = 4, nullable = false)
    private BigDecimal quantidade;

    @Column(name = "AJU_OBSERVACAO", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "AJU_MOVIMENTACAO", nullable = false)
    private LocalDateTime dataMovimentacao = LocalDateTime.now();

    @Column(name = "AJU_LOTE", length = 50)
    private String lote;

    @Column(name = "AJU_VALIDADE")
    private LocalDate validade;

    public EstoqueAjuste() {
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

    public Entidade getLocal() {
        return local;
    }

    public void setLocal(Entidade local) {
        this.local = local;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public MotivoAjuste getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAjuste motivo) {
        this.motivo = motivo;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
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
        EstoqueAjuste that = (EstoqueAjuste) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
