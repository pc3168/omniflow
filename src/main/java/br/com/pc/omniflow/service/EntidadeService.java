package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.TipoDocumento;
import br.com.pc.omniflow.domain.model.Entidade;
import br.com.pc.omniflow.domain.repository.EntidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadeService extends BaseService{

    private final EntidadeRepository repository;

    public EntidadeService(EntidadeRepository repository) {
        this.repository = repository;
    }

    public Entidade buscarOuCriarPorDocumento(Long gruId, String documento, String nomeRazao, String uf) {
        return comFiltro(gruId, () -> {
            return repository.findByDocumento(documento)
                    .orElseGet(() -> {
                        log.info(this.getClass(), "Criando nova entidade: " + nomeRazao + " (" + documento + ")");

                        TipoDocumento tipoDocumento = documento.length() >= 14 ? TipoDocumento.CNPJ : TipoDocumento.CPF;

                        Entidade nova = new Entidade();
                        nova.setDocumento(documento);
                        nova.setNome(nomeRazao);
                        nova.setCodigoExterno(null);
//                        nova.setTipo(TipoEntidade.EXTERNO);
                        nova.setTipoDocumento(tipoDocumento);
                        nova.setUf(uf);

                        return this.salvar(gruId, repository, nova);
                    });
        });
    }

    public List<Entidade> listarTodos(Long gruId) {
        return comFiltro(gruId, repository::findAll);
    }

    public void excluir(Long gruId, Long id) {
        deletar(gruId, repository, id);
    }
}
