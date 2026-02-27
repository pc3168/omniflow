package br.com.pc.omniflow.exception;

public class OmniFlowException extends RuntimeException {

    public OmniFlowException(String mensagem) {
        super(mensagem);
    }

    public OmniFlowException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
