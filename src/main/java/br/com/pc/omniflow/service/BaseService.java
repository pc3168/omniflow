package br.com.pc.omniflow.service;

import br.com.pc.omniflow.config.GrupoFilterHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

@Transactional
public abstract class BaseService {

    @Autowired
    private GrupoFilterHelper grupoFilter;

    protected <T> T comFiltro(Long gruId, Supplier<T> query) {
        return grupoFilter.execute(gruId, query);
    }
}
