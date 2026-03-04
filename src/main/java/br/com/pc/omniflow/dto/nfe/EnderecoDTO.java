package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EnderecoDTO {
    @JacksonXmlProperty(localName = "xLgr")
    private String logradouro;

    @JacksonXmlProperty(localName = "nro")
    private String numero;

    @JacksonXmlProperty(localName = "xBairro")
    private String bairro;

    @JacksonXmlProperty(localName = "cMun")
    private String codigoMunicipio;

    @JacksonXmlProperty(localName = "xMun")
    private String nomeMunicipio;

    @JacksonXmlProperty(localName = "UF")
    private String uf;

    public EnderecoDTO() {
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
