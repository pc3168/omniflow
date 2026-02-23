package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO_CONVERSAO", uniqueConstraints = {
    /* Atenção: Unique constraints com colunas NULL podem variar por banco.
       No Postgres, para garantir a unicidade, podemos precisar de um índice parcial
       ou tratar na Service. Vamos manter a lógica simples primeiro:
    */
        @UniqueConstraint(name = "UK_PROD_FORN_UNIDADE", columnNames = {"PROD_ID", "FORN_ID", "CONV_UNIDADE_ENTRADA"})
})
public class ProdutoConversao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONV_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROD_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "FORN_ID", nullable = true) // NULL significa "Padrão para qualquer fornecedor"
    private Entidade fornecedor;

    @Column(name = "CONV_UNIDADE_ENTRADA", length = 10, nullable = false)
    private String unidadeEntrada; // Ex: "CX"

    @Column(name = "CONV_FATOR", precision = 15, scale = 4, nullable = false)
    private BigDecimal fator;

    @Column(name = "CONV_UNIDADE_SAIDA", length = 10, nullable = false)
    private String unidadeSaida; // Ex: "UN"

    // Getters e Setters
}
