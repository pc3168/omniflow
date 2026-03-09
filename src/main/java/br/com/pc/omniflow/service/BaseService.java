package br.com.pc.omniflow.service;

import br.com.pc.omniflow.config.GrupoFilterHelper;
import br.com.pc.omniflow.domain.model.GrupoEmpresa;
import br.com.pc.omniflow.domain.model.TenantEntity;
import br.com.pc.omniflow.exception.OmniFlowException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.function.Supplier;

public abstract class BaseService {

    @Autowired
    private GrupoFilterHelper grupoFilter;

    @Autowired
    protected LogService log;

    @Transactional
    protected <T> T comFiltro(Long gruId, Supplier<T> query) {
        return grupoFilter.execute(gruId, query);
    }

    protected <T extends TenantEntity> T salvar(Long gruId, JpaRepository<T, ?> repository, T entidade) {
//        return comFiltro(gruId, () -> {
//            GrupoEmpresa grupo = new GrupoEmpresa(gruId);
//            entidade.setGrupo(grupo);
//            return repository.save(entidade);
//        });
            entidade.setGrupo(new GrupoEmpresa(gruId));
            return repository.save(entidade);
    }

    protected <T> void deletar(Long gruId, JpaRepository<T, Long> repository, Long id) {
        comFiltro(gruId, () -> {
            // Verificamos se existe antes de deletar para evitar EmptyResultDataAccessException
            // e garantir que o filtro de grupo seja aplicado no 'exists'
            if (repository.existsById(id)) {
                repository.deleteById(id);
                log.info(this.getClass(), "Registro " + id + " deletado com sucesso.");
            } else {
                log.info(this.getClass(), "Tentativa de deletar registro " + id + " inexistente ou fora do grupo.");
            }
            return null;
        });
    }

    /**
     * Busca por ID padrão com filtro de grupo
     */
    protected <T> Optional<T> buscarPorId(Long gruId, JpaRepository<T, Long> repository, Long id) {
        return comFiltro(gruId, () -> repository.findById(id));
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
