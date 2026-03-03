package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.Entidade;
import br.com.pc.omniflow.domain.model.Produto;
import br.com.pc.omniflow.domain.model.ProdutoFornecedor;
import br.com.pc.omniflow.domain.repository.ProdutoFornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoFornecedorService extends BaseService {

    private final ProdutoFornecedorRepository repository;

    public ProdutoFornecedorService(ProdutoFornecedorRepository repository) {
        this.repository = repository;
    }

    public Optional<ProdutoFornecedor> buscarVinculo(Long gruId, Long entidadeId, String codigoNoFornecedor) {
        return comFiltro(gruId, () ->
                repository.findByFornecedorIdAndCodigoNoFornecedor(entidadeId, codigoNoFornecedor)
        );
    }

    public ProdutoFornecedor criarVinculo(Long gruId, Produto produto, Entidade fornecedor, String codigoNoFornecedor) {
        ProdutoFornecedor pf = new ProdutoFornecedor();
        pf.setProduto(produto);
        pf.setFornecedor(fornecedor);
        pf.setCodigoNoFornecedor(codigoNoFornecedor);

        return this.salvar(gruId, repository, pf);
    }

    public ProdutoFornecedor buscarOuCriarVinculo(Long gruId, Entidade fornecedor, Produto produtoInterno, String codigoNoFornecedor) {
        return comFiltro(gruId, () -> {
            return repository.findByFornecedorAndCodigoNoFornecedor(fornecedor, codigoNoFornecedor)
                    .orElseGet(() -> {
                        log.info(this.getClass(), "Criando vínculo De-Para: Fornecedor " + fornecedor.getNome() + " -> Produto " + produtoInterno.getSku());
                        ProdutoFornecedor novoVinculo = new ProdutoFornecedor();
                        novoVinculo.setFornecedor(fornecedor);
                        novoVinculo.setProduto(produtoInterno);
                        novoVinculo.setCodigoNoFornecedor(codigoNoFornecedor);
                        return this.salvar(gruId, repository, novoVinculo);
                    });
        });
    }

    public void excluir(Long gruId, Long id) {
        comFiltro(gruId, () -> {
            repository.deleteById(id);
            return null;
        });
    }
}
