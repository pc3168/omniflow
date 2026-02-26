package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "NFE_TOTAIS")
public class NfeTotais extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CAB_ID", nullable = false)
    private NfeCabecalho cabecalho;

    @Column(name = "TOT_VNF", precision = 15, scale = 2)
    private BigDecimal valorNota = BigDecimal.ZERO;

    @Column(name = "TOT_VPROD", precision = 15, scale = 2)
    private BigDecimal valorProdutos = BigDecimal.ZERO;

    @Column(name = "TOT_VBC", precision = 15, scale = 2)
    private BigDecimal baseCalculoIcms = BigDecimal.ZERO; //<vbc>

    @Column(name = "TOT_VICMS", precision = 15, scale = 2)
    private BigDecimal valorIcms = BigDecimal.ZERO; //<vICMS>

    @Column(name = "TOT_VST", precision = 15, scale = 2)
    private BigDecimal valorIcmsSt = BigDecimal.ZERO;

    @Column(name = "TOT_VFRETE", precision = 15, scale = 2)
    private BigDecimal valorFrete = BigDecimal.ZERO;

    @Column(name = "TOT_VSEG", precision = 15, scale = 2)
    private BigDecimal valorSeguro = BigDecimal.ZERO;

    @Column(name = "TOT_VDESC", precision = 15, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "TOT_VOUTRO", precision = 15, scale = 2)
    private BigDecimal outrasDespesas = BigDecimal.ZERO; //<vOutro>

    @Column(name = "TOT_VIPI", precision = 15, scale = 2)
    private BigDecimal valorIpi = BigDecimal.ZERO;

    @Column(name = "TOT_VPIS", precision = 15, scale = 2)
    private BigDecimal valorPis = BigDecimal.ZERO;

    @Column(name = "TOT_VCOFINS", precision = 15, scale = 2)
    private BigDecimal valorCofins = BigDecimal.ZERO;

    @Column(name = "TOT_VBCST", precision = 15, scale = 2)
    private BigDecimal baseCalculoIcmsSt = BigDecimal.ZERO; // <vBCST>

    @Column(name = "TOT_VFCP", precision = 15, scale = 2)
    private BigDecimal valorFcp = BigDecimal.ZERO; // <vFCP>

    @Column(name = "TOT_VFCPST", precision = 15, scale = 2)
    private BigDecimal valorFcpSt = BigDecimal.ZERO; // <vFCPST>

    @Column(name = "TOT_VIPIDEVOL", precision = 15, scale = 2)
    private BigDecimal valorIpiDevol = BigDecimal.ZERO; // <vIPIDevol>

    @Column(name = "TOT_VICMSDESON", precision = 15, scale = 2)
    private BigDecimal valorIcmsDeson = BigDecimal.ZERO; // <vICMSDeson> - ICMS Desonerado

    @Column(name = "TOT_VTOTTRIB", precision = 15, scale = 2)
    private BigDecimal valorTotalTributos = BigDecimal.ZERO; // <vTotTrib> - Valor aproximado dos tributos (IBPT)

    @Column(name = "TOT_VII", precision = 15, scale = 2)
    private BigDecimal valorIi = BigDecimal.ZERO; // <vII> - Imposto de Importação

    @Column(name = "TOT_VFCPSTRET", precision = 15, scale = 2)
    private BigDecimal valorFcpStRet = BigDecimal.ZERO; // <vFCPSTRet> - FCP retido anteriormente por ST

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

    public BigDecimal getBaseCalculoIcmsSt() {
        return baseCalculoIcmsSt;
    }

    public void setBaseCalculoIcmsSt(BigDecimal baseCalculoIcmsSt) {
        this.baseCalculoIcmsSt = baseCalculoIcmsSt;
    }

    public BigDecimal getValorFcp() {
        return valorFcp;
    }

    public void setValorFcp(BigDecimal valorFcp) {
        this.valorFcp = valorFcp;
    }

    public BigDecimal getValorFcpSt() {
        return valorFcpSt;
    }

    public void setValorFcpSt(BigDecimal valorFcpSt) {
        this.valorFcpSt = valorFcpSt;
    }

    public BigDecimal getValorIpiDevol() {
        return valorIpiDevol;
    }

    public void setValorIpiDevol(BigDecimal valorIpiDevol) {
        this.valorIpiDevol = valorIpiDevol;
    }

    public BigDecimal getValorIcmsDeson() {
        return valorIcmsDeson;
    }

    public void setValorIcmsDeson(BigDecimal valorIcmsDeson) {
        this.valorIcmsDeson = valorIcmsDeson;
    }

    public BigDecimal getValorTotalTributos() {
        return valorTotalTributos;
    }

    public void setValorTotalTributos(BigDecimal valorTotalTributos) {
        this.valorTotalTributos = valorTotalTributos;
    }

    public BigDecimal getValorIi() {
        return valorIi;
    }

    public void setValorIi(BigDecimal valorIi) {
        this.valorIi = valorIi;
    }

    public BigDecimal getValorFcpStRet() {
        return valorFcpStRet;
    }

    public void setValorFcpStRet(BigDecimal valorFcpStRet) {
        this.valorFcpStRet = valorFcpStRet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NfeTotais nfeTotais = (NfeTotais) o;
        return Objects.equals(id, nfeTotais.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}