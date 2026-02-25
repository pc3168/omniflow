package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.NfeXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfeXmlRepository extends JpaRepository<NfeXml, Long> {

}
