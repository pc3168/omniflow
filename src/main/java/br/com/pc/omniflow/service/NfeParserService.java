package br.com.pc.omniflow.service;

import org.springframework.stereotype.Service;

@Service
public class NfeParserService extends BaseService {

//    private final NfeXmlRepository nfeXmlRepository;
//    private final XmlMapper xmlMapper;
//    // Aqui você injetaria os repositórios finais:
//    // private final NfeCabecalhoRepository cabecalhoRepository;
//
//    public NfeParserService(NfeXmlRepository nfeXmlRepository, XmlMapper xmlMapper) {
//        this.nfeXmlRepository = nfeXmlRepository;
//        this.xmlMapper = xmlMapper;
//    }
//
//    /**
//     * Tenta achar a entidade pelo CNPJ/CPF. Se não achar, cadastra.
//     */
//    private Entidade buscarOuCriarEntidade(EmitDestDTO dto) {
//        if (dto == null) return null;
//
//        String documento = dto.getDocumento();
//        return entidadeRepository.findByDocumento(documento)
//                .orElseGet(() -> {
//                    log.info(this.getClass(), "Criando nova entidade (automático): " + dto.getNome());
//                    Entidade nova = new Entidade();
//                    nova.setDocumento(documento);
//                    nova.setNomeRazao(dto.getNome());
//                    nova.setNomeFantasia(dto.getNomeFantasia());
//                    // O BaseEntity cuidará do gru_id através do filtro/interceptor
//                    return entidadeRepository.save(nova);
//                });
//    }
//
//    /**
//     * Verifica na tabela de De-Para se já conhecemos esse código desse fornecedor.
//     */
//    private Produto buscarProdutoPeloDePara(Entidade fornecedor, String codProdFornecedor) {
//        return deParaRepository.findByFornecedorAndCodigoNoFornecedor(fornecedor, codProdFornecedor)
//                .map(ProdutoFornecedor::getProduto)
//                .orElse(null);
//    }
//
//    /**
//     * Se o De-Para falhou, tentamos achar o produto no nosso cadastro global (ex: pelo EAN).
//     * Se ainda assim não existir, criamos um novo produto.
//     */
//    private Produto buscarOuCriarProduto(ProdDTO prodDto) {
//        // Tenta buscar pelo código de barras (EAN) se ele vier no XML
//        if (prodDto.getEan() != null && !prodDto.getEan().isEmpty() && !prodDto.getEan().equals("SEM GTIN")) {
//            Optional<Produto> prodEan = produtoRepository.findByCodigoBarras(prodDto.getEan());
//            if (prodEan.isPresent()) return prodEan.get();
//        }
//
//        // Se não achou, cria um novo produto no seu catálogo
//        log.info(this.getClass(), "Cadastrando novo produto: " + prodDto.getDescricao());
//        Produto novo = new Produto();
//        novo.setDescricao(prodDto.getDescricao());
//        novo.setUnidadeMedida(prodDto.getUnidade());
//        // Aqui você pode definir um SKU interno ou usar o EAN
//        return produtoRepository.save(novo);
//    }
//
//    /**
//     * Salva o relacionamento para que na próxima nota do mesmo fornecedor
//     * o sistema já saiba qual é o seu produto interno.
//     */
//    private void criarVinculoDePara(Produto meuProduto, Entidade fornecedor, String codNoFornecedor) {
//        ProdutoFornecedor dePara = new ProdutoFornecedor();
//        dePara.setProduto(meuProduto);
//        dePara.setFornecedor(fornecedor);
//        dePara.setCodigoNoFornecedor(codNoFornecedor);
//        deParaRepository.save(dePara);
//    }
//
//
//
//    /**
//     * Busca todos os XMLs pendentes e processa um a um.
//     */
//    public void processarPendentes(Long gruId) {
//        comFiltro(gruId, () -> {
//            // Busca registros com status 'RECEBIDO' para o grupo atual
//            List<NfeXml> pendentes = nfeXmlRepository.findByStatusProcessamento(StatusProcessamento.RECEBIDO);
//
//            log.info(this.getClass(), "Encontrados " + pendentes.size() + " XMLs para processar.");
//
//            for (NfeXml nfeXml : pendentes) {
//                try {
//                    processarConteudoXml(nfeXml);
//                } catch (Exception e) {
//                    nfeXml.setStatusProcessamento(StatusProcessamento.ERRO);
//                    nfeXml.setLogErro(e.getMessage());
//                    nfeXmlRepository.save(nfeXml);
//                    log.erro(this.getClass(), "Falha ao processar ID: " + nfeXml.getId(), e);
//                }
//            }
//            return null;
//        });
//    }
//
//    private void processarConteudoXml(NfeXml nfeXml) throws Exception {
//        NfeProcDTO nfeDto = xmlMapper.readValue(nfeXml.getXmlOriginal(), NfeProcDTO.class);
//        InfNfeDTO inf = nfeDto.getNfe().getInfNFe();
//
//        // --- ETAPA 1: TRATAR ENTIDADES (Emitente e Destinatário) ---
//        // Se a nota é de ENTRADA, o Emitente é o Fornecedor.
//        // Se a nota é de SAÍDA (Filial), o Destinatário é o Cliente.
//        Entidade fornecedor = buscarOuCriarEntidade(inf.getEmitente());
//        Entidade destino = buscarOuCriarEntidade(inf.getDestinatario());
//
//        // --- ETAPA 2: TRATAR PRODUTOS E DE-PARA ---
//        for (DetDTO det : inf.getItens()) {
//            ProdDTO prodDto = det.getProduto();
//
//            // 1. Busca se já existe o De-Para para esse fornecedor + código cProd
//            Produto produtoInterno = buscarProdutoPeloDePara(fornecedor, prodDto.getCodigo());
//
//            if (produtoInterno == null) {
//                // 2. Se não tem De-Para, busca pelo EAN (como fallback) ou cria novo produto
//                produtoInterno = buscarOuCriarProduto(prodDto);
//
//                // 3. Cria o vínculo de De-Para para a próxima vez
//                criarVinculoDePara(produtoInterno, fornecedor, prodDto.getCodigo());
//            }
//
//            // --- ETAPA 3: AGORA SIM, ESTOQUE ---
//            // Aqui você já tem o 'produtoInterno' garantido e a 'entidade' garantida
//            atualizarEstoque(produtoInterno, prodDto.getQuantidade(), nfeXml);
//        }
//    }
}
