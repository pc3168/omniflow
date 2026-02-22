package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ESTOQUE_FECHAMENTOS")
public class EstoqueFechamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEC_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ENT_ID", nullable = false)
    private Entidade local; // Onde está

    @ManyToOne
    @JoinColumn(name = "ENT_ENT_ID", nullable = false)
    private Entidade proprietario; // De quem é

    @Column(name = "FEC_MES_REFERENCIA", nullable = false)
    private Integer mes;

    @Column(name = "FEC_ANO_REFERENCIA", nullable = false)
    private Integer ano;

    @Column(name = "FEC_QUANTIDADE_FINAL", precision = 15, scale = 4, nullable = false)
    private BigDecimal quantidadeFinal;

    @Column(name = "FEC_LOTE", length = 50)
    private String lote;

    @Column(name = "FEC_VALIDADE")
    private LocalDate validade;

    public EstoqueFechamento() {}

    // Getters e Setters...
}
