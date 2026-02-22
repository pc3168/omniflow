package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ESTOQUE_AJUSTES")
public class EstoqueAjuste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AJU_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "ENT_ID", nullable = false)
    private Entidade local; // Onde o ajuste foi feito

    @ManyToOne
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @Column(name = "AJU_MOTIVO", length = 50, nullable = false)
    private String motivo;

    @Column(name = "AJU_OBSERVACAO", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "AJU_MOVIMENTACAO", nullable = false)
    private LocalDateTime dataMovimentacao;

    @Column(name = "AJU_LOTE", length = 50)
    private String lote;

    @Column(name = "AJU_VALIDADE")
    private LocalDate validade;

    public EstoqueAjuste() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GrupoEmpresa getGrupo() { return grupo; }
    public void setGrupo(GrupoEmpresa grupo) { this.grupo = grupo; }

    public Entidade getLocal() { return local; }
    public void setLocal(Entidade local) { this.local = local; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public String getLote() { return lote; }
    public void setLote(String lote) { this.lote = lote; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
}
