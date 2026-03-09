package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "PRODUTOS_EAN", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"EAN_CODIGO", "GRU_ID"})
})
public class ProdutoEan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @Column(name = "EAN_CODIGO", length = 14, nullable = false)
    private String ean;

    public ProdutoEan() {
    }

    public ProdutoEan(Long gruId) {
        this.grupo = new GrupoEmpresa(gruId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoEan that = (ProdutoEan) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
