package br.com.pc.omniflow.domain.enums;

public enum OrigemMovimento {
    NFE_ENTRADA("NE"),   // Compra comum
    NFE_SAIDA("NS"),     // Venda comum
    AJU_ESTOQUE("AJ"),   // Perda/Avaria
    TRANSFERENCIA("TR"), // Entre suas próprias unidades
    REMESSA_TERC("RT"),  // Saindo para conserto, demonstração ou armazém externo
    RETORNO_TERC("RE");  // Voltando do terceiro para o seu estoque

    private final String codigo;
    OrigemMovimento(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }

    public static OrigemMovimento fromCodigo(String codigo) {
        for (OrigemMovimento o : OrigemMovimento.values()) {
            if (o.getCodigo().equals(codigo)) return o;
        }
        return AJU_ESTOQUE; // Default
    }
}
