package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUE_SALDOS")
public class EstoqueSaldo {
    @EmbeddedId
    private EstoqueSaldoId id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "PRO_ID")
    private Produto produto;

    @ManyToOne
    @MapsId("localId")
    @JoinColumn(name = "ENT_LOCAL")
    private Entidade local;

    @ManyToOne
    @MapsId("proprietarioId")
    @JoinColumn(name = "ENT_PROPRIETARIO")
    private Entidade proprietario;

    @Column(name = "ES_QUANTIDADE_TOTAL", precision = 15, scale = 4, nullable = false, columnDefinition = "DECIMAL(15,4) DEFAULT 0.0" )
    private BigDecimal quantidadeTotal = BigDecimal.ZERO;

    @Column(name = "ES_DATA_ULTIMA_MOVIMENTACAO", nullable = false)
    private LocalDateTime dataUltimaMovimentacao = LocalDateTime.now();

    @Column(name = "ES_VALIDADE")
    private LocalDate validade;
}
