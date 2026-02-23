package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "GRUPO_EMPRESAS")
public class GrupoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRU_ID")
    private Long id;

    @Column(name = "GRU_NOME", nullable = false)
    private String nome;

    public GrupoEmpresa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEmpresa that = (GrupoEmpresa) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}