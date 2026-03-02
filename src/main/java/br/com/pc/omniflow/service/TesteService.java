package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.Entidade;
import br.com.pc.omniflow.domain.repository.EntidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class TesteService extends BaseService{

    private final EntidadeRepository entidadeRepository;

    public TesteService(EntidadeRepository entidadeRepository) {
        this.entidadeRepository = entidadeRepository;
    }

    public void teste(Long gruId){

        comFiltro(gruId, entidadeRepository::findAll);
    }

    public void teste2(Long gruId) {

        // Criando o Supplier explicitamente (classe anônima)
        Supplier<List<Entidade>> minhaQuery = new Supplier<List<Entidade>>() {
            @Override
            public List<Entidade> get() {
                return entidadeRepository.findAll();
            }
        };

        List<Entidade> resultado = comFiltro(gruId, minhaQuery);
    }
}
