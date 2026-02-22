package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ENTIDADES")
public class Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "ENT_DOCUMENTO", length = 14, nullable = false)
    private String documento;

    @Column(name = "ENT_NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "ENT_TIPO", length = 1, nullable = false)
    private String tipo; // F, L, E, T

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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
