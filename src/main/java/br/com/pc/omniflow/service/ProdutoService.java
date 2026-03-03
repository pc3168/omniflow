package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.TipoProduto;
import br.com.pc.omniflow.domain.model.Produto;
import br.com.pc.omniflow.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends BaseService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto buscarOuCriar(Long gruId, String sku, String descricao) {
        return comFiltro(gruId, () -> {
            return repository.findBySku(sku)
                    .orElseGet(() -> {
                        log.info(this.getClass(), "Produto novo detectado: " + sku);
                        Produto novo = new Produto();
                        novo.setSku(sku);
                        novo.setDescricao(descricao);
                        novo.setTipo(TipoProduto.SIMPLES);
                        return this.salvar(gruId, repository, novo);
                    });
        });
    }

    public void excluir(Long gruId, Long id) {
        deletar(gruId, repository, id);
    }
}
