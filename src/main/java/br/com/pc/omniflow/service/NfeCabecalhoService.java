package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.NfeCabecalho;
import br.com.pc.omniflow.domain.repository.NfeCabecalhoRepository;
import org.springframework.stereotype.Service;

@Service
public class NfeCabecalhoService extends BaseService{

    private final NfeCabecalhoRepository cabecalhoRepository;

    public NfeCabecalhoService(NfeCabecalhoRepository cabecalhoRepository) {
        this.cabecalhoRepository = cabecalhoRepository;
    }

    public NfeCabecalho salvar(Long gruId, NfeCabecalho cabecalho) {

        if (cabecalho == null) {
            lancarErro("Cabecalho não pode ser nulo");
        }

        return salvar(gruId, cabecalhoRepository, cabecalho);
    }

    public void excluir(Long gruId, Long cabecalhoId){
        deletar(gruId, cabecalhoRepository, cabecalhoId);
    }
}
