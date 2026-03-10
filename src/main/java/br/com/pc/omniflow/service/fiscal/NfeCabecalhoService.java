package br.com.pc.omniflow.service.fiscal;

import br.com.pc.omniflow.domain.model.NfeCabecalho;
import br.com.pc.omniflow.domain.repository.NfeCabecalhoRepository;
import br.com.pc.omniflow.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NfeCabecalhoService extends BaseService {

    private final NfeCabecalhoRepository cabecalhoRepository;

    public NfeCabecalhoService(NfeCabecalhoRepository cabecalhoRepository) {
        this.cabecalhoRepository = cabecalhoRepository;
    }


//    public NfeCabecalho salvar(Long gruId, NfeCabecalho cabecalho) {
//
//        if (cabecalho == null) {
//            lancarErro("Cabecalho não pode ser nulo");
//            return null;
//        }
//
//        return this.salvar(gruId, cabecalhoRepository, cabecalho);
//    }

    public NfeCabecalho salvar(Long gruId, NfeCabecalho cabecalho) {
        // Verifique se já existe um cabeçalho para este XML
        Optional<NfeCabecalho> existente = cabecalhoRepository.findByNfeXmlId(cabecalho.getNfeXml().getId());

        if (existente.isPresent()) {
            // Opção A: Lançar erro amigável
            lancarErro("Este XML já foi processado e possui um cabeçalho (ID: " + existente.get().getId() + ")");

            // Opção B: Atualizar o existente (Merge) em vez de criar novo
            cabecalho.setId(existente.get().getId());
        }

        return this.salvar(gruId, cabecalhoRepository, cabecalho);
    }

    public void excluir(Long gruId, Long cabecalhoId){
        deletar(gruId, cabecalhoRepository, cabecalhoId);
    }
}
