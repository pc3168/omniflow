package br.com.pc.omniflow.domain.enums;

public enum MotivoAjuste {
    AVARIA("A"),
    EXTRAVIO("E"),
    ERRO_CONTAGEM("C");

    private final String codigo;

    MotivoAjuste(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static MotivoAjuste fromCodigo(String codigo) {
        for (MotivoAjuste m : MotivoAjuste.values()) {
            if (m.getCodigo().equals(codigo)) return m;
        }
        throw new IllegalArgumentException("Motivo de ajuste inválido: " + codigo);
    }
}
