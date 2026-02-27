package br.com.pc.omniflow.dto.nfe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EmitDestDTO {

    @JacksonXmlProperty(localName = "CNPJ")
    private String cnpj;

    @JacksonXmlProperty(localName = "CPF")
    private String cpf;

    @JacksonXmlProperty(localName = "xNome")
    private String nome;

    @JacksonXmlProperty(localName = "xFant")
    private String nomeFantasia;

    public EmitDestDTO() {
    }

    // Método auxiliar para pegar o documento independente de ser CNPJ ou CPF
    public String getDocumento() {
        return (cnpj != null && !cnpj.isEmpty()) ? cnpj : cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
