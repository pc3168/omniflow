package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUE_MOVIMENTACAO")
public class EstoqueMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOV_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ENT_ENT_ID", nullable = false)
    private Entidade proprietario; // O dono legal

    @ManyToOne
    @JoinColumn(name = "ENT_ID", nullable = false)
    private Entidade local; // Onde está fisicamente

    @Column(name = "MOV_QUANTIDADE", nullable = false)
    private BigDecimal quantidade;

    @Column(name = "MOV_SINAL", length = 1, nullable = false)
    private String sinal; // + ou -

    @Column(name = "MOV_LOTE", nullable = false)
    private String lote;

    @Column(name = "MOV_DATA_MOVIMENTO", nullable = false)
    private LocalDateTime dataMovimento;
}
