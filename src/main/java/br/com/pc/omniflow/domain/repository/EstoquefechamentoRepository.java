package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.EstoqueFechamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoquefechamentoRepository extends JpaRepository<EstoqueFechamento, Long> {
}
