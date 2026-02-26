package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.NfeXml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NfeXmlRepository extends JpaRepository<NfeXml, Long> {

    // O Spring Data gera o SQL automaticamente:
    // SELECT count(*) > 0 FROM NFE_XMLS WHERE NFE_CHAVE_ACESSO = ? AND GRU_ID = ?
    boolean existsByChaveAcesso(String chaveAcesso);

}
