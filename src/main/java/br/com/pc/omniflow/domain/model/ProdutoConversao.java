package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "PRODUTO_CONVERSAO", uniqueConstraints = {
    /* Atenção: Unique constraints com colunas NULL podem variar por banco.
       No Postgres, para garantir a unicidade, podemos precisar de um índice parcial
       ou tratar na Service. Vamos manter a lógica simples primeiro:
    */
        @UniqueConstraint(name = "UK_PROD_FORN_UNIDADE", columnNames = {"PROD_ID", "FORN_ID", "CONV_UNIDADE_ENTRADA"})
})
public class ProdutoConversao extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONV_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PROD_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "FORN_ID", nullable = true) // NULL significa "Padrão para qualquer fornecedor"
    private Entidade fornecedor;

    @Column(name = "CONV_UNIDADE_ENTRADA", length = 10, nullable = false)
    private String unidadeEntrada; // Ex: "CX"

    @Column(name = "CONV_FATOR", precision = 15, scale = 4, nullable = false)
    private BigDecimal fator;

    @Column(name = "CONV_UNIDADE_SAIDA", length = 10, nullable = false)
    private String unidadeSaida; // Ex: "UN"

    public ProdutoConversao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entidade getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Entidade fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getUnidadeEntrada() {
        return unidadeEntrada;
    }

    public void setUnidadeEntrada(String unidadeEntrada) {
        this.unidadeEntrada = unidadeEntrada;
    }

    public BigDecimal getFator() {
        return fator;
    }

    public void setFator(BigDecimal fator) {
        this.fator = fator;
    }

    public String getUnidadeSaida() {
        return unidadeSaida;
    }

    public void setUnidadeSaida(String unidadeSaida) {
        this.unidadeSaida = unidadeSaida;
    }

    @Override
    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoConversao that = (ProdutoConversao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
