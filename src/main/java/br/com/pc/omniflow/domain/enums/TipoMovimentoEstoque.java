package br.com.pc.omniflow.domain.enums;

public enum TipoMovimentoEstoque {
    SOMA("+"),
    SUBTRAI("-"),
    NENHUM("N"); // Ex: Remessa para conserto que não afeta estoque financeiro

    private final String simbolo;

    TipoMovimentoEstoque(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static TipoMovimentoEstoque fromSimbolo(String simbolo){
        for (TipoMovimentoEstoque tipo : TipoMovimentoEstoque.values()){
            if(tipo.getSimbolo().equals(simbolo) ){
                return tipo;
            }
        }
        return NENHUM;
    }
}
