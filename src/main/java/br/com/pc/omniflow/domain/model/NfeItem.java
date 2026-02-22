package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "NFE_ITENS")
public class NfeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CAB_ID", nullable = false)
    private NfeCabecalho cabecalho;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @Column(name = "ITEM_QUANTIDADE", precision = 15, scale = 4)
    private BigDecimal quantidade;

    @Column(name = "ITEM_VALOR_UNITARIO", precision = 15, scale = 4)
    private BigDecimal valorUnitario;

    @Column(name = "ITEM_LOTE")
    private String lote;

    @Column(name = "ITEM_VALIDADE")
    private LocalDate validade;

}
