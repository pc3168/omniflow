package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "NFE_ITENS")
public class NfeItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "CAB_ID", nullable = false)
    private NfeCabecalho cabecalho;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "REG_CFOP", nullable = false)
    private RegraCfop regraCfop;

    @Column(name = "ITE_NUMERO", nullable = false)
    private Integer numeroItem; // nItem no XML

    @Column(name = "ITEM_QUANTIDADE", precision = 15, scale = 4, nullable = false)
    private BigDecimal quantidade;

    @Column(name = "ITEM_VALOR_UNITARIO", precision = 15, scale = 4, nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "ITEM_NCM",length =  8, nullable = false)
    private String ncm;

    @Column(name = "ITEM_EAN" , length = 20, nullable = false)
    private String ean;

    @Column(name = "ITEM_UNIDADE", length = 4, nullable = false)
    private String unidade;

    @Column(name = "ITEM_LOTE", length = 50, nullable = false, columnDefinition = "Varchar(50) DEFAULT 'ND'")
    private String lote = "ND";

    @Column(name = "ITEM_VALIDADE")
    private LocalDate validade;

    @Column(name = "ITEM_FABRICAO")
    private LocalDate fabricacao;

    @Column(name = "ITE_PROCESSADO", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean processado = false;
    // false = Ainda não gerou registro na ESTOQUE_MOVIMENTACAO
    // true  = Já impactou o estoque

    @Column(name = "ITE_DATA_PROCESSAMENTO")
    private LocalDateTime dataProcessamento;
    // Para auditoria: saber quando o estoque foi realmente atualizado

    public NfeItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NfeCabecalho getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(NfeCabecalho cabecalho) {
        this.cabecalho = cabecalho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    public LocalDate getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(LocalDate fabricacao) {
        this.fabricacao = fabricacao;
    }

    public RegraCfop getRegraCfop() {
        return regraCfop;
    }

    public void setRegraCfop(RegraCfop regraCfop) {
        this.regraCfop = regraCfop;
    }

    @Override
    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    public boolean isProcessado() {
        return processado;
    }

    public void setProcessado(boolean processado) {
        this.processado = processado;
    }

    public LocalDateTime getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(LocalDateTime dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NfeItem nfeItem = (NfeItem) o;
        return Objects.equals(id, nfeItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
