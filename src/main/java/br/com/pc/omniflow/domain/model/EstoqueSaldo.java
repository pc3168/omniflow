package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ESTOQUE_SALDOS")
public class EstoqueSaldo {
    @EmbeddedId
    private EstoqueSaldoId id;

    @ManyToOne
    @MapsId("grupoId")
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    // O @MapsId diz ao JPA: "O produtoId desta entidade é o mesmo que está dentro da PK"
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

    public EstoqueSaldo() {
    }

    public EstoqueSaldo(GrupoEmpresa grupo, Entidade local, Entidade proprietario, Produto produto, String lote) {
        // Inicializa o ID composto
        this.id = new EstoqueSaldoId(
                grupo.getId(),
                local.getId(),
                proprietario.getId(),
                produto.getId(),
                lote
        );
        // Inicializa as referências de objeto para o @MapsId
        this.grupo = grupo;
        this.local = local;
        this.proprietario = proprietario;
        this.produto = produto;
        this.quantidadeTotal = BigDecimal.ZERO;
        this.dataUltimaMovimentacao = LocalDateTime.now();
    }

    public EstoqueSaldoId getId() {
        return id;
    }

    public void setId(EstoqueSaldoId id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Entidade getLocal() {
        return local;
    }

    public void setLocal(Entidade local) {
        this.local = local;
    }

    public Entidade getProprietario() {
        return proprietario;
    }

    public void setProprietario(Entidade proprietario) {
        this.proprietario = proprietario;
    }

    public BigDecimal getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(BigDecimal quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public LocalDateTime getDataUltimaMovimentacao() {
        return dataUltimaMovimentacao;
    }

    public void setDataUltimaMovimentacao(LocalDateTime dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EstoqueSaldo that = (EstoqueSaldo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
