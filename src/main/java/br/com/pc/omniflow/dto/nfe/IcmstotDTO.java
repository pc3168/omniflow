package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

public class IcmstotDTO {

    @JacksonXmlProperty(localName = "vBC")
    private BigDecimal valorBaseCalculo;

    @JacksonXmlProperty(localName = "vICMS")
    private BigDecimal valorIcms;

    @JacksonXmlProperty(localName = "vICMSDeson")
    private BigDecimal valorIcmsDeson;

    @JacksonXmlProperty(localName = "vFCP")
    private BigDecimal valorFcp;

    @JacksonXmlProperty(localName = "vBCST")
    private BigDecimal valorBaseCalculoSt;

    @JacksonXmlProperty(localName = "vST")
    private BigDecimal valorIcmsSt;

    @JacksonXmlProperty(localName = "vFCPST")
    private BigDecimal valorFcpSt;

    @JacksonXmlProperty(localName = "vFCPSTRet")
    private BigDecimal valorFcpStRet;

    @JacksonXmlProperty(localName = "vProd")
    private BigDecimal valorProdutos;

    @JacksonXmlProperty(localName = "vFrete")
    private BigDecimal valorFrete;

    @JacksonXmlProperty(localName = "vSeg")
    private BigDecimal valorSeguro;

    @JacksonXmlProperty(localName = "vDesc")
    private BigDecimal valorDesconto;

    @JacksonXmlProperty(localName = "vII")
    private BigDecimal valorImpostoImportacao;

    @JacksonXmlProperty(localName = "vIPI")
    private BigDecimal valorIpi;

    @JacksonXmlProperty(localName = "vIPIDevol")
    private BigDecimal valorIpiDevol;

    @JacksonXmlProperty(localName = "vPIS")
    private BigDecimal valorPis;

    @JacksonXmlProperty(localName = "vCOFINS")
    private BigDecimal valorCofins;

    @JacksonXmlProperty(localName = "vOutro")
    private BigDecimal valorOutrasDespesas;

    @JacksonXmlProperty(localName = "vNF")
    private BigDecimal valorTotalNota;

    @JacksonXmlProperty(localName = "vTotTrib")
    private BigDecimal valorTotalTributos;

    public BigDecimal getValorBaseCalculo() {
        return valorBaseCalculo;
    }

    public void setValorBaseCalculo(BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public BigDecimal getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(BigDecimal valorIcms) {
        this.valorIcms = valorIcms;
    }

    public BigDecimal getValorIcmsDeson() {
        return valorIcmsDeson;
    }

    public void setValorIcmsDeson(BigDecimal valorIcmsDeson) {
        this.valorIcmsDeson = valorIcmsDeson;
    }

    public BigDecimal getValorFcp() {
        return valorFcp;
    }

    public void setValorFcp(BigDecimal valorFcp) {
        this.valorFcp = valorFcp;
    }

    public BigDecimal getValorBaseCalculoSt() {
        return valorBaseCalculoSt;
    }

    public void setValorBaseCalculoSt(BigDecimal valorBaseCalculoSt) {
        this.valorBaseCalculoSt = valorBaseCalculoSt;
    }

    public BigDecimal getValorIcmsSt() {
        return valorIcmsSt;
    }

    public void setValorIcmsSt(BigDecimal valorIcmsSt) {
        this.valorIcmsSt = valorIcmsSt;
    }

    public BigDecimal getValorFcpSt() {
        return valorFcpSt;
    }

    public void setValorFcpSt(BigDecimal valorFcpSt) {
        this.valorFcpSt = valorFcpSt;
    }

    public BigDecimal getValorFcpStRet() {
        return valorFcpStRet;
    }

    public void setValorFcpStRet(BigDecimal valorFcpStRet) {
        this.valorFcpStRet = valorFcpStRet;
    }

    public BigDecimal getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(BigDecimal valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(BigDecimal valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorImpostoImportacao() {
        return valorImpostoImportacao;
    }

    public void setValorImpostoImportacao(BigDecimal valorImpostoImportacao) {
        this.valorImpostoImportacao = valorImpostoImportacao;
    }

    public BigDecimal getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(BigDecimal valorIpi) {
        this.valorIpi = valorIpi;
    }

    public BigDecimal getValorIpiDevol() {
        return valorIpiDevol;
    }

    public void setValorIpiDevol(BigDecimal valorIpiDevol) {
        this.valorIpiDevol = valorIpiDevol;
    }

    public BigDecimal getValorPis() {
        return valorPis;
    }

    public void setValorPis(BigDecimal valorPis) {
        this.valorPis = valorPis;
    }

    public BigDecimal getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }

    public BigDecimal getValorOutrasDespesas() {
        return valorOutrasDespesas;
    }

    public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
        this.valorOutrasDespesas = valorOutrasDespesas;
    }

    public BigDecimal getValorTotalNota() {
        return valorTotalNota;
    }

    public void setValorTotalNota(BigDecimal valorTotalNota) {
        this.valorTotalNota = valorTotalNota;
    }

    public BigDecimal getValorTotalTributos() {
        return valorTotalTributos;
    }

    public void setValorTotalTributos(BigDecimal valorTotalTributos) {
        this.valorTotalTributos = valorTotalTributos;
    }
}
