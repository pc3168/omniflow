package br.com.pc.omniflow.domain.enums;

public enum TipoXml {
    NFE("nfeProc"),
    NFE_CANCELAMENTO("procEventoNFe"),
    NFE_INUTILIZACAO("procInutNFe");

    private final String tagRaiz;

    TipoXml(String tagRaiz) {
        this.tagRaiz = tagRaiz;
    }

    public String getTagRaiz() {
        return tagRaiz;
    }

    public static TipoXml identificar(String conteudoXml) {
        for (TipoXml tipo : values()) {
            if (conteudoXml.contains("<" + tipo.tagRaiz)) {
                return tipo;
            }
        }
        return null;
    }
}
