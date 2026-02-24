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
}
