package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.Entidade;
import br.com.pc.omniflow.domain.model.ProdutoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoFornecedorRepository extends JpaRepository<ProdutoFornecedor, Long> {

    /**
     * Busca o vínculo exato:
     * Qual o meu produto interno para o código X do fornecedor Y?
     */
    Optional<ProdutoFornecedor> findByFornecedorAndCodigoNoFornecedor(Entidade fornecedor, String codigoNoFornecedor);

    // Opcional: Busca pela ID do fornecedor em vez do objeto completo
    Optional<ProdutoFornecedor> findByFornecedorIdAndCodigoNoFornecedor(Long fornecedorId, String codigoNoFornecedor);
}
