package br.com.pc.omniflow.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "CFOP")
public class Cfop {

    @Id
    @Column(name = "CFOP_CODIGO", length = 4)
    private String codigo; // Ex: 5102

    @Column(name = "CFOP_DESCRICAO", length = 150)
    private String descricao; // Descrição oficial da SEFAZ

    public Cfop() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cfop cfop = (Cfop) o;
        return Objects.equals(codigo, cfop.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
