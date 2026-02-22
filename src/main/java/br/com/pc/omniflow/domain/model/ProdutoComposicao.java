package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRODUTOS_COMPOSICAO")
public class ProdutoComposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_COM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produtoPai; // O KIT

    @ManyToOne
    @JoinColumn(name = "PRO_PRO_ID", nullable = false)
    private Produto produtoFilho; // O COMPONENTE

    @Column(name = "PRO_QUANTIDADE", nullable = false)
    private Integer quantidade;

    public ProdutoComposicao() {}

    // Getters e Setters...
}
