package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DetDTO {

    @JacksonXmlProperty(isAttribute = true, localName = "nItem")
    private Integer numeroItem;

    @JacksonXmlProperty(localName = "prod")
    private ProdDTO produto;

    public DetDTO() {
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public ProdDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdDTO produto) {
        this.produto = produto;
    }
}
