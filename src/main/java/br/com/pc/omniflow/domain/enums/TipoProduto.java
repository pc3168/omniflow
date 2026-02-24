package br.com.pc.omniflow.domain.enums;

public enum TipoProduto {
    SIMPLES("S"),
    KIT("K"),
    SERVICO("V"); // "V" de Serviço ou conforme sua preferência

    private final String codigo;

    TipoProduto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoProduto fromCodigo(String codigo) {
        for (TipoProduto tipo : TipoProduto.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return SIMPLES; // Default caso venha algo estranho
    }
}
