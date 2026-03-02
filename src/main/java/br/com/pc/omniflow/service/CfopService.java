package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.Cfop;
import br.com.pc.omniflow.domain.repository.CfopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CfopService {

    private final CfopRepository repository;

    public CfopService(CfopRepository repository) {
        this.repository = repository;
    }

    public Optional<Cfop> buscarPorCodigo(String codigo) {
        return repository.findById(codigo);
    }

    public Cfop salvarGlobal(Cfop cfop) {
        return repository.save(cfop);
    }

    public List<Cfop> listarTodos() {
        return repository.findAll();
    }

}
