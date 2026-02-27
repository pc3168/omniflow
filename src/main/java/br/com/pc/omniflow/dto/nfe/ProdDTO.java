package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

public class ProdDTO {

    @JacksonXmlProperty(localName = "cProd")
    private String codigo;

    @JacksonXmlProperty(localName = "xProd")
    private String descricao;

    @JacksonXmlProperty(localName = "uCom")
    private String unidade;

    @JacksonXmlProperty(localName = "qCom")
    private BigDecimal quantidade;

    @JacksonXmlProperty(localName = "vUnCom")
    private BigDecimal valorUnitario;

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
}
