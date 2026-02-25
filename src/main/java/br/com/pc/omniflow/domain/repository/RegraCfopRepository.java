package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.RegraCfop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegraCfopRepository extends JpaRepository<RegraCfop, Long> {

}
