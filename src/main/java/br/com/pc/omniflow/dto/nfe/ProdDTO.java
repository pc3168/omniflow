package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;
import java.util.List;

public class ProdDTO {

    @JacksonXmlProperty(localName = "cProd")
    private String codigo;

    @JacksonXmlProperty(localName = "cEAN")
    private String ean;

    @JacksonXmlProperty(localName = "CFOP")
    private String cfop;

    @JacksonXmlProperty(localName = "xProd")
    private String descricao;

    @JacksonXmlProperty(localName = "uCom")
    private String unidade;

    @JacksonXmlProperty(localName = "qCom")
    private BigDecimal quantidade;

    @JacksonXmlProperty(localName = "vUnCom")
    private BigDecimal valorUnitario;

    @JacksonXmlProperty(localName = "NCM")
    private String ncm;

    @JacksonXmlProperty(localName = "rastro")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<RastroDTO> rastros;

    public ProdDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public List<RastroDTO> getRastros() {
        return rastros;
    }

    public void setRastros(List<RastroDTO> rastros) {
        this.rastros = rastros;
    }
}
