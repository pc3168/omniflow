package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.math.BigDecimal;

public class RastroDTO {

    @JacksonXmlProperty(localName = "nLote")
    private String numeroLote;

    @JacksonXmlProperty(localName = "qLote")
    private BigDecimal quantidadeLote;

    @JacksonXmlProperty(localName = "dFab")
    private String dataFabricacao; // String para conversão manual

    @JacksonXmlProperty(localName = "dVal")
    private String dataValidade;

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public BigDecimal getQuantidadeLote() {
        return quantidadeLote;
    }

    public void setQuantidadeLote(BigDecimal quantidadeLote) {
        this.quantidadeLote = quantidadeLote;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }
}
