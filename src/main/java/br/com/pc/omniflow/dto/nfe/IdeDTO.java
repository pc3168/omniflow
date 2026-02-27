package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IdeDTO {

    @JacksonXmlProperty(localName = "nNF")
    private String numero;

    @JacksonXmlProperty(localName = "serie")
    private String serie;

    @JacksonXmlProperty(localName = "dhEmi")
    private String dataEmissao;

    public IdeDTO() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
