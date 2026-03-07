package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.enums.TipoEntidade;
import br.com.pc.omniflow.domain.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

    Optional<Entidade> findByDocumento(String documento);

    boolean existsByDocumentoAndTipo(String documento, TipoEntidade tipo);
}
