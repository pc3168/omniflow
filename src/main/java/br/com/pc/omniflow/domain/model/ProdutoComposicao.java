package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUTOS_COMPOSICAO")
public class ProdutoComposicao extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_COM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_KIT", nullable = false)
    private Produto produtokit; // O KIT

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produtoFilho; // O COMPONENTE

    @Column(name = "PRO_QUANTIDADE", precision = 15, scale = 4, nullable = false, columnDefinition = "DECIMAL(15,4) DEFAULT 1.0" )
    private BigDecimal quantidade = BigDecimal.ONE;

    public ProdutoComposicao() {}


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

    public Produto getProdutokit() {
        return produtokit;
    }

    public void setProdutokit(Produto produtokit) {
        this.produtokit = produtokit;
    }

    public Produto getProdutoFilho() {
        return produtoFilho;
    }

    public void setProdutoFilho(Produto produtoFilho) {
        this.produtoFilho = produtoFilho;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoComposicao that = (ProdutoComposicao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
