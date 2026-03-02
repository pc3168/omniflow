package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.Cfop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CfopRepository extends JpaRepository<Cfop, String> {


}
