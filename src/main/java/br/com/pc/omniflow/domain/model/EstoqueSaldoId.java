package br.com.pc.omniflow.domain.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EstoqueSaldoId implements Serializable {
    private Long grupoId;
    private Long proprietarioId; // ENT_ENT_ID
    private Long localId;        // ENT_ID
    private Long produtoId;
    private String lote;
}
