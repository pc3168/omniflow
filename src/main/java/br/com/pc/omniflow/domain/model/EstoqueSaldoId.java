package br.com.pc.omniflow.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EstoqueSaldoId implements Serializable {

    @Column(name = "GRU_ID")
    private Long grupoId;

    private Long localId;        // ENT_ID

    private Long proprietarioId; // ENT_ENT_ID

    private Long produtoId;

    @Column(name = "LOTE", length = 50)
    private String lote;

    public EstoqueSaldoId(Long grupoId, Long localId, Long proprietarioId, Long produtoId, String lote) {
        this.grupoId = grupoId;
        this.localId = localId;
        this.proprietarioId = proprietarioId;
        this.produtoId = produtoId;
        this.lote = lote == null && lote.isEmpty() ? "ND" : lote;
    }

    public EstoqueSaldoId() {
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    public Long getProprietarioId() {
        return proprietarioId;
    }

    public void setProprietarioId(Long proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
