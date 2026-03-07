package br.com.pc.omniflow.service.cadastro;

import br.com.pc.omniflow.domain.model.Produto;
import br.com.pc.omniflow.domain.model.ProdutoEan;
import br.com.pc.omniflow.domain.repository.ProdutoEanRepository;
import br.com.pc.omniflow.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoEanService extends BaseService {

    private final ProdutoEanRepository repository;

    public ProdutoEanService(ProdutoEanRepository repository) {
        this.repository = repository;
    }

    public Optional<ProdutoEan> buscarPorEan(Long gruId, String ean) {
        return comFiltro(gruId, () -> repository.findByEan(ean));
    }

    public ProdutoEan buscarOuSalvarEan(Long gruId, Produto produto, String eanCodigo) {
        return buscarPorEan(gruId, eanCodigo).orElseGet(() -> {
            log.info(this.getClass(), "Cadastrando o Ean " + eanCodigo);
            ProdutoEan ean = new ProdutoEan();
            ean.setProduto(produto);
            ean.setEan(eanCodigo);
            return this.salvar(gruId, repository, ean);
        });
    }
}