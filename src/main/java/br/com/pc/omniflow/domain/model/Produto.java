package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUTOS")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "PRO_SKU", length = 50, nullable = false)
    private String sku;

    @Column(name = "PRO_DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "PRO_ISKIT", nullable = false)
    private boolean isKit;
}