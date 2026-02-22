package br.com.pc.omniflow.domain.enums;

public enum TipoEntidade {

    FILIAL('F'),
    LOGISTICA('L'),
    EXTERNO('E'),
    TRANSPORTE('T');

    public final char codigo;

    TipoEntidade(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public static TipoEntidade fromCodigo(char codigo){
        for (TipoEntidade tipo : TipoEntidade.values()){
            if(tipo.getCodigo() == codigo){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de entidade inválido: " + codigo);
    }
}
