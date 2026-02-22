package br.com.pc.omniflow.domain.enums;

public enum TipoDocumento {
    CNPJ("0"),
    CPF("1");

    private final String codigo;

    TipoDocumento(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoDocumento fromCodigo(String codigo) {
        for (TipoDocumento tipo : TipoDocumento.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de Documento inválido: " + codigo);
    }
}
