package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "GRUPO_EMPRESAS")
public class GrupoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GRU_ID")
    private Long id;

    @Column(name = "GRU_NOME", nullable = false)
    private String nome;

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
}