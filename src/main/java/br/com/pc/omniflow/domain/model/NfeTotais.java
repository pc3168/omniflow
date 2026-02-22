package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "NFE_TOTAIS")
public class NfeTotais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @OneToOne
    @JoinColumn(name = "CAB_ID", nullable = false)
    private NfeCabecalho cabecalho;

    @Column(name = "TOT_VNF", precision = 15, scale = 2)
    private BigDecimal valorNota;

    @Column(name = "TOT_VPROD", precision = 15, scale = 2)
    private BigDecimal valorProdutos;

    @Column(name = "TOT_VBC", precision = 15, scale = 2)
    private BigDecimal baseCalculoIcms;

    @Column(name = "TOT_VICMS", precision = 15, scale = 2)
    private BigDecimal valorIcms;

    @Column(name = "TOT_VST", precision = 15, scale = 2)
    private BigDecimal valorIcmsSt;

    @Column(name = "TOT_VFRETE", precision = 15, scale = 2)
    private BigDecimal valorFrete;

    @Column(name = "TOT_VSEG", precision = 15, scale = 2)
    private BigDecimal valorSeguro;

    @Column(name = "TOT_VDESC", precision = 15, scale = 2)
    private BigDecimal valorDesconto;

    @Column(name = "TOT_VOUTRO", precision = 15, scale = 2)
    private BigDecimal outrasDespesas;

    @Column(name = "TOT_VIPI", precision = 15, scale = 2)
    private BigDecimal valorIpi;

    @Column(name = "TOT_VPIS", precision = 15, scale = 2)
    private BigDecimal valorPis;

    @Column(name = "TOT_VCOFINS", precision = 15, scale = 2)
    private BigDecimal valorCofins;

    public NfeTotais() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GrupoEmpresa getGrupo() { return grupo; }
    public void setGrupo(GrupoEmpresa grupo) { this.grupo = grupo; }

    public NfeCabecalho getCabecalho() { return cabecalho; }
    public void setCabecalho(NfeCabecalho cabecalho) { this.cabecalho = cabecalho; }

    public BigDecimal getValorNota() { return valorNota; }
    public void setValorNota(BigDecimal valorNota) { this.valorNota = valorNota; }

    public BigDecimal getValorProdutos() { return valorProdutos; }
    public void setValorProdutos(BigDecimal valorProdutos) { this.valorProdutos = valorProdutos; }

    public BigDecimal getBaseCalculoIcms() { return baseCalculoIcms; }
    public void setBaseCalculoIcms(BigDecimal baseCalculoIcms) { this.baseCalculoIcms = baseCalculoIcms; }

    public BigDecimal getValorIcms() { return valorIcms; }
    public void setValorIcms(BigDecimal valorIcms) { this.valorIcms = valorIcms; }

    public BigDecimal getValorIcmsSt() { return valorIcmsSt; }
    public void setValorIcmsSt(BigDecimal valorIcmsSt) { this.valorIcmsSt = valorIcmsSt; }

    public BigDecimal getValorFrete() { return valorFrete; }
    public void setValorFrete(BigDecimal valorFrete) { this.valorFrete = valorFrete; }

    public BigDecimal getValorSeguro() { return valorSeguro; }
    public void setValorSeguro(BigDecimal valorSeguro) { this.valorSeguro = valorSeguro; }

    public BigDecimal getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(BigDecimal valorDesconto) { this.valorDesconto = valorDesconto; }

    public BigDecimal getOutrasDespesas() { return outrasDespesas; }
    public void setOutrasDespesas(BigDecimal outrasDespesas) { this.outrasDespesas = outrasDespesas; }

    public BigDecimal getValorIpi() { return valorIpi; }
    public void setValorIpi(BigDecimal valorIpi) { this.valorIpi = valorIpi; }

    public BigDecimal getValorPis() { return valorPis; }
    public void setValorPis(BigDecimal valorPis) { this.valorPis = valorPis; }

    public BigDecimal getValorCofins() { return valorCofins; }
    public void setValorCofins(BigDecimal valorCofins) { this.valorCofins = valorCofins; }
}