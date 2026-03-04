package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.ProdutoComposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoComposicaoRepository extends JpaRepository<ProdutoComposicao, Long> {

    List<ProdutoComposicao> findByProdutokit(Long produtokit);

}
