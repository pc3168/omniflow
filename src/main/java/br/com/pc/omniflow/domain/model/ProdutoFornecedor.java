package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PRODUTO_FORNECEDOR")
public class ProdutoFornecedor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID")
    private Produto produto; // Seu código interno

    @ManyToOne
    @JoinColumn(name = "ENT_ID")
    private Entidade fornecedor; // O fornecedor dono desse código

    @Column(name = "PRO_ID_FORNECEDOR")
    private String codigoNoFornecedor; // O cProd do XML

    public ProdutoFornecedor() {
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

    public String getCodigoNoFornecedor() {
        return codigoNoFornecedor;
    }

    public void setCodigoNoFornecedor(String codigoNoFornecedor) {
        this.codigoNoFornecedor = codigoNoFornecedor;
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
        ProdutoFornecedor that = (ProdutoFornecedor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
