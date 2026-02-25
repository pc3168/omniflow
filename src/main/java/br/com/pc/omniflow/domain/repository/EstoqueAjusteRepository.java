package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.EstoqueAjuste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueAjusteRepository extends JpaRepository<EstoqueAjuste, Long> {

}
