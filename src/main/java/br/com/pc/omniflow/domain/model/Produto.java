package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.TipoProdutoConverter;
import br.com.pc.omniflow.domain.enums.TipoProduto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PRODUTOS", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PRODUTO_GRUPO_CODIGO", columnNames = {"GRU_ID", "PRO_SKU"})
})
public class Produto extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoConversao> conversoes = new ArrayList<>();

    @OneToMany(mappedBy = "produtokit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoComposicao> itensDoKit = new ArrayList<>();

    @Column(name = "PRO_SKU", length = 50, nullable = false)
    private String sku;

    @Column(name = "PRO_DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Convert(converter = TipoProdutoConverter.class)
    @Column(name = "PRO_TIPO", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'S' ")
    private TipoProduto tipo = TipoProduto.SIMPLES;

    public Produto() {
    }

    public void addConversao(ProdutoConversao conversao) {
        conversoes.add(conversao);
        conversao.setProduto(this);
    }

    public void addComponente(ProdutoComposicao componente) {
        itensDoKit.add(componente);
        componente.setProdutokit(this);
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ProdutoConversao> getConversoes() {
        return conversoes;
    }

    public void setConversoes(List<ProdutoConversao> conversoes) {
        this.conversoes = conversoes;
    }

    public List<ProdutoComposicao> getItensDoKit() {
        return itensDoKit;
    }

    public void setItensDoKit(List<ProdutoComposicao> itensDoKit) {
        this.itensDoKit = itensDoKit;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}