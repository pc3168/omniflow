package br.com.pc.omniflow.domain.enums;

public enum ModeloDocumento {
    NFE("55"),
    NFCE("65");

    private final String codigo;
    ModeloDocumento(String codigo) { this.codigo = codigo; }
    public String getCodigo() { return codigo; }

    public static ModeloDocumento fromCodigo(String codigo) {
        for (ModeloDocumento m : values()) {
            if (m.codigo.equals(codigo)) return m;
        }
        throw new IllegalArgumentException("Tipo de Modelo inválido: " + codigo);
    }
}
