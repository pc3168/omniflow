package br.com.pc.omniflow.dto.nfe;

import br.com.pc.omniflow.domain.enums.ModeloDocumento;
import br.com.pc.omniflow.domain.enums.TipoOperacao;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IdeDTO {

    @JacksonXmlProperty(localName = "nNF")
    private String numero;

    @JacksonXmlProperty(localName = "serie")
    private String serie;

    @JacksonXmlProperty(localName = "dhEmi")
    private String dataEmissao;

    @JacksonXmlProperty(localName = "mod")
    private String modelo;

    @JacksonXmlProperty(localName = "tpNF")
    private String tipoOperacao;

    @JacksonXmlProperty(localName = "natOp")
    private String naturezaOperacao;

    public IdeDTO() {
    }

    public ModeloDocumento getModeloEnum() {
        return ModeloDocumento.fromCodigo(this.modelo);
    }

    public TipoOperacao getTipoOperacaoEnum() {
        return TipoOperacao.fromCodigo(this.tipoOperacao);
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }
}
