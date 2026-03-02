package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.CfopRegra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CfopRegraRepository extends JpaRepository<CfopRegra, String> {

    Optional<CfopRegra> findByCfopCodigo(String codigo);
}
