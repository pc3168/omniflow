package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.model.NfeXml;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NfeXmlRepository extends JpaRepository<NfeXml, Long> {

    // O Spring Data gera o SQL automaticamente:
    // SELECT count(*) > 0 FROM NFE_XMLS WHERE NFE_CHAVE_ACESSO = ? AND GRU_ID = ?
    boolean existsByChaveAcesso(String chaveAcesso);

    // O Spring Data gera: SELECT * FROM NFE_XMLS WHERE NF_STATUS_PROCESSAMENTO = ? AND GRU_ID = ?
//    List<NfeXml> findByStatusProcessamento(StatusProcessamento statusProcessamento);

    // O Pageable permite passar o limite e a ordenação
    List<NfeXml> findByStatusProcessamento(StatusProcessamento status, Pageable pageable);

    List<NfeXml> findByStatusProcessamentoIn(List<StatusProcessamento> status, Pageable pageable);
}
