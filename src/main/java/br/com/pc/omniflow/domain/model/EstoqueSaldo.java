package br.com.pc.omniflow.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUE_SALDOS")
public class EstoqueSaldo {
    @EmbeddedId
    private EstoqueSaldoId id;

    @Column(name = "ES_QUANTIDADE_TOTAL", nullable = false)
    private BigDecimal quantidadeTotal;

    @Column(name = "ES_DATA_ULTIMA_MOVIMENTACAO", nullable = false)
    private LocalDateTime dataUltimaMovimentacao;

    @Column(name = "ES_VALIDADE")
    private LocalDate validade;
}
