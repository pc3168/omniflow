package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.TipoDocumento;
import br.com.pc.omniflow.domain.model.Entidade;
import br.com.pc.omniflow.domain.repository.EntidadeRepository;
import br.com.pc.omniflow.dto.nfe.EmitDestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadeService extends BaseService{

    private final EntidadeRepository repository;

    public EntidadeService(EntidadeRepository repository) {
        this.repository = repository;
    }

    public Entidade buscarOuCriarPorDocumento(Long gruId, EmitDestDTO dto) {
        return comFiltro(gruId, () -> {
            return repository.findByDocumento(dto.getDocumento())
                    .orElseGet(() -> {
                        log.info(this.getClass(), "Criando nova entidade: " + dto.getNomeFantasia() + " (" + dto.getDocumento() + ")");

                        TipoDocumento tipoDocumento = dto.getDocumento().length() >= 14 ? TipoDocumento.CNPJ : TipoDocumento.CPF;

                        Entidade nova = new Entidade();
                        nova.setDocumento(dto.getDocumento());
                        nova.setNome(dto.getNome());
                        nova.setCodigoExterno(null);
//                        nova.setTipo(TipoEntidade.EXTERNO);
                        nova.setTipoDocumento(tipoDocumento);
                        nova.setUf(dto.getUf());

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
