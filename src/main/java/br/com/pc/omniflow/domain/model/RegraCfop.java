package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "REGRA_CFOP")
public class RegraCfop {

    @Id
    @Column(name = "REG_CFOP", length = 4)
    private String cfop; // A PK é o próprio código (ex: 5102)

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "REG_DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "REG_OPERACAO", length = 1, nullable = false)
    private String operacao; // 'E'ntrada ou 'S'aída

    @Column(name = "REG_AFETA_ESTOQUE", nullable = false)
    private boolean afetaEstoque;

    public RegraCfop() {}

    // Getters e Setters...
}
