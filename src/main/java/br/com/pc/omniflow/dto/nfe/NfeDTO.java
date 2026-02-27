package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class NfeDTO {

    @JacksonXmlProperty(localName = "infNFe")
    private InfNfeDTO infNFe;

    public NfeDTO() {
    }

    public InfNfeDTO getInfNFe() {
        return infNFe;
    }

    public void setInfNFe(InfNfeDTO infNFe) {
        this.infNFe = infNFe;
    }
}
