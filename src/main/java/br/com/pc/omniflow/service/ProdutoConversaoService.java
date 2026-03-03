package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.ProdutoConversao;
import br.com.pc.omniflow.domain.repository.ProdutoConversaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoConversaoService extends BaseService {

    private final ProdutoConversaoRepository repository;

    public ProdutoConversaoService(ProdutoConversaoRepository repository) {
        this.repository = repository;
    }

    /**
     * Busca se existe uma regra de conversão para o produto e a unidade que veio no XML.
     */
    public Optional<ProdutoConversao> buscarConversao(Long gruId, Long produtoId, String unidadeXml) {
        return comFiltro(gruId, () ->
                repository.findByProdutoIdAndUnidadeEntrada(produtoId, unidadeXml)
        );
    }

    public ProdutoConversao salvarConversao(Long gruId, ProdutoConversao conversao) {
        return this.salvar(gruId, repository, conversao);
    }

    public void excluir(Long gruId, Long id) {
        this.deletar(gruId, repository, id);
    }
}
