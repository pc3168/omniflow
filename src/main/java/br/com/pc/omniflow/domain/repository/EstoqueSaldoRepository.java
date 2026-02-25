package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.EstoqueSaldo;
import br.com.pc.omniflow.domain.model.EstoqueSaldoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueSaldoRepository extends JpaRepository<EstoqueSaldo, EstoqueSaldoId> {

}
