package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.ProdutoConversao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoConversaoRepository extends JpaRepository<ProdutoConversao, Long> {

    Optional<ProdutoConversao> findByProdutoIdAndUnidadeEntrada(Long produtoId, String unidadeEntrada);

}
