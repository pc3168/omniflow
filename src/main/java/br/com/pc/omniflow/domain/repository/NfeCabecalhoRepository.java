package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.NfeCabecalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NfeCabecalhoRepository extends JpaRepository<NfeCabecalho, Long> {


    Optional<NfeCabecalho> findByNfeXmlId(Long id);
}
