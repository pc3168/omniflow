package br.com.pc.omniflow.service;

import br.com.pc.omniflow.config.GrupoFilterHelper;
import br.com.pc.omniflow.exception.OmniFlowException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

@Transactional
public abstract class BaseService {

    @Autowired
    private GrupoFilterHelper grupoFilter;

    @Autowired
    protected LogService log;

    protected <T> T comFiltro(Long gruId, Supplier<T> query) {
        return grupoFilter.execute(gruId, query);
    }

    /**
     * Registra o erro no log e lança a exceção personalizada
     */
    protected void lancarErro(String mensagem, Throwable e) {
        log.erro(this.getClass(), mensagem, e);
        throw new OmniFlowException(mensagem, e);
    }

    protected void lancarErro(String mensagem) {
        log.erro(this.getClass(), mensagem, null);
        throw new OmniFlowException(mensagem);
    }
}
