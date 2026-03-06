package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TotalDTO {

    @JacksonXmlProperty(localName = "ICMSTot")
    private IcmstotDTO icmsTot;

    public IcmstotDTO getIcmsTot() {
        return icmsTot;
    }

    public void setIcmsTot(IcmstotDTO icmsTot) {
        this.icmsTot = icmsTot;
    }
}
