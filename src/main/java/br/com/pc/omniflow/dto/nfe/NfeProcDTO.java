package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "nfeProc")
//@JacksonXmlRootElement(localName = "nfeProc", namespace = "http://www.portalfiscal.inf.br/nfe")
public class NfeProcDTO {

    @JacksonXmlProperty(localName = "NFe")
    private NfeDTO nfe;

    public NfeProcDTO() {
    }

    public NfeDTO getNfe() {
        return nfe;
    }

    public void setNfe(NfeDTO nfe) {
        this.nfe = nfe;
    }
}
