package br.com.pc.omniflow.domain.enums;

public enum TipoOperacao {
    ENTRADA("0"),
    SAIDA("1");

    private final String codigo;

    TipoOperacao(String codigo) { this.codigo = codigo; }

    public String getCodigo() { return codigo; }

    public static TipoOperacao fromCodigo(String codigo) {
        for (TipoOperacao t : values()) {
            if (t.codigo.equals(codigo)) return t;
        }
        throw new IllegalArgumentException("Tipo de Operação inválida: " + codigo);
    }
}
