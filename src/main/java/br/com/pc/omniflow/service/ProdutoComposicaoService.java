package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.ProdutoComposicao;
import br.com.pc.omniflow.domain.repository.ProdutoComposicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoComposicaoService extends BaseService {

    private final ProdutoComposicaoRepository repository;

    public ProdutoComposicaoService(ProdutoComposicaoRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os itens que compõem um produto (Kit ou Produção).
     */
    public List<ProdutoComposicao> listarComponentes(Long gruId, Long produtoPaiId) {
        return comFiltro(gruId, () ->
                repository.findByProdutokit(produtoPaiId)
        );
    }

    public ProdutoComposicao adicionarItemComposicao(Long gruId, ProdutoComposicao item) {
        // Aqui você pode validar se o produtoPaiId não é igual ao produtoFilhoId (evitar loop)
        return this.salvar(gruId, repository, item);
    }

    public void excluir(Long gruId, Long id) {
        this.deletar(gruId, repository, id);
    }
}
