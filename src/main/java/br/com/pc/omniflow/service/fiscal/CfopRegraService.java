package br.com.pc.omniflow.service.fiscal;

import br.com.pc.omniflow.domain.enums.StatusRegra;
import br.com.pc.omniflow.domain.enums.TipoMovimentoEstoque;
import br.com.pc.omniflow.domain.model.Cfop;
import br.com.pc.omniflow.domain.model.CfopRegra;
import br.com.pc.omniflow.domain.repository.CfopRegraRepository;
import br.com.pc.omniflow.service.BaseService;
import br.com.pc.omniflow.service.cadastro.CfopService;
import org.springframework.stereotype.Service;

@Service
public class CfopRegraService extends BaseService {

    private final CfopRegraRepository repository;
    private final CfopService cfopService;

    public CfopRegraService(CfopRegraRepository repository, CfopService cfopService) {
        this.repository = repository;
        this.cfopService = cfopService;
    }

    public CfopRegra buscarPendente(String cfop){
        return repository.findByStatusAndCfopCodigo(StatusRegra.PENDENTE, cfop).orElse(null);
    }

    public CfopRegra buscarOuCriarRegra(Long gruId, String codigoCfop) {
        return comFiltro(gruId, () -> {
            // 1. Tenta achar a regra já configurada para o grupo
            return repository.findByCfopCodigo(codigoCfop)
                    .orElseGet(() -> {
                        log.info(this.getClass(), "Criando regra de estoque pendente para CFOP: " + codigoCfop);

                        // 2. Verifica se o CFOP existe no dicionário global
                        Cfop cfopMestre = cfopService.buscarPorCodigo(codigoCfop)
                                .orElseGet(() -> cfopService.buscarOuSalvar(codigoCfop, "CFOP não catalogado"));

                        // 3. Cria a regra específica do grupo
                        CfopRegra novaRegra = new CfopRegra();
                        novaRegra.setCfop(cfopMestre);
                        novaRegra.setDescricao("Configuração automática - Revisão necessária");
                        novaRegra.setStatus(StatusRegra.PENDENTE); // Trava o estoque
                        novaRegra.setSinalEstoque(TipoMovimentoEstoque.NENHUM);

                        return this.salvar(gruId, repository, novaRegra);
                    });
        });
    }

    public void excluir(Long gruId, String id) {
        comFiltro(gruId, () -> {
            if (repository.existsById(id)) {
                repository.deleteById(id);
                log.info(this.getClass(), "Registro " + id + " deletado com sucesso.");
            } else {
                log.info(this.getClass(), "Tentativa de deletar registro " + id + " inexistente ou fora do grupo.");
            }
            return null;
        });
    }

    public String sugerirCfopEntrada(String cfopXml, boolean isNotaPropria) {
        if (cfopXml == null || cfopXml.length() < 4) return cfopXml;

        char primeiroDigito = cfopXml.charAt(0);

        // Se a nota é própria de entrada (1, 2 ou 3), mantém o que está
        if (isNotaPropria && (primeiroDigito == '1' || primeiroDigito == '2' || primeiroDigito == '3')) {
            return cfopXml;
        }

        // De-Para de Saída (5, 6, 7) para Entrada (1, 2, 3)
        return switch (primeiroDigito) {
            case '5' -> "1" + cfopXml.substring(1); // Interna
            case '6' -> "2" + cfopXml.substring(1); // Interestadual
            case '7' -> "3" + cfopXml.substring(1); // Exterior
            default -> cfopXml; // Se já for entrada ou outro caso
        };
    }

    public TipoMovimentoEstoque definirSinalAutomatico(String cfop, boolean isNotaPropria) {
        char primeiroDigito = cfop.charAt(0);

        // Se a nota é DE TERCEIRO (Compra)
        if (!isNotaPropria) {
            // Se o fornecedor mandou 5xxx, 6xxx ou 7xxx, para MIM é uma ENTRADA (SOMA)
            if (primeiroDigito == '5' || primeiroDigito == '6' || primeiroDigito == '7') {
                return TipoMovimentoEstoque.SOMA;
            }
        }

        // Se a nota é PRÓPRIA (Emissão sua)
        else {
            // Se eu emiti um 5xxx, 6xxx ou 7xxx, é uma SAÍDA (SUBTRAI)
            if (primeiroDigito == '5' || primeiroDigito == '6' || primeiroDigito == '7') {
                return TipoMovimentoEstoque.SUBTRAI;
            }
            // Se eu emiti um 1xxx, 2xxx ou 3xxx (Nota de entrada própria), é SOMA
            if (primeiroDigito == '1' || primeiroDigito == '2' || primeiroDigito == '3') {
                return TipoMovimentoEstoque.SOMA;
            }
        }

        return TipoMovimentoEstoque.NENHUM;
    }
}
