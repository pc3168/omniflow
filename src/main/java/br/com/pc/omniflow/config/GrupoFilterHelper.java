package br.com.pc.omniflow.config;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class GrupoFilterHelper {

    private final EntityManager entityManager;

    public GrupoFilterHelper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> T execute(Long gruId, Supplier<T> query) {
        if (gruId == null) {
            throw new IllegalArgumentException("O ID do grupo não pode ser nulo para esta operação.");
        }

        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("grupoFilter").setParameter("grupoId", gruId);
        try {
            return query.get();
        } finally {
            session.disableFilter("grupoFilter"); // sempre limpa!
        }
    }
}
