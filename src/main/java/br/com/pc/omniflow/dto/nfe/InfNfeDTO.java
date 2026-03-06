package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class InfNfeDTO {

    @JacksonXmlProperty(isAttribute = true, localName = "Id")
    private String id; // Ex: NFe3524...

    @JacksonXmlProperty(localName = "ide")
    private IdeDTO ide;

    @JacksonXmlProperty(localName = "emit")
    private EmitDestDTO emitente;

    @JacksonXmlProperty(localName = "dest")
    private EmitDestDTO destinatario;

    @JacksonXmlProperty(localName = "det")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<DetDTO> itens;

    @JacksonXmlProperty(localName = "total")
    private TotalDTO total;


    public InfNfeDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdeDTO getIde() {
        return ide;
    }

    public void setIde(IdeDTO ide) {
        this.ide = ide;
    }

    public EmitDestDTO getEmitente() {
        return emitente;
    }

    public void setEmitente(EmitDestDTO emitente) {
        this.emitente = emitente;
    }

    public EmitDestDTO getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(EmitDestDTO destinatario) {
        this.destinatario = destinatario;
    }

    public List<DetDTO> getItens() {
        return itens;
    }

    public void setItens(List<DetDTO> itens) {
        this.itens = itens;
    }

    public TotalDTO getTotal() {
        return total;
    }

    public void setTotal(TotalDTO total) {
        this.total = total;
    }
}
