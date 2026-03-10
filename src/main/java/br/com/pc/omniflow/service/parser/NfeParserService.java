package br.com.pc.omniflow.service.parser;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.enums.TipoMovimentoEstoque;
import br.com.pc.omniflow.domain.enums.TipoXml;
import br.com.pc.omniflow.domain.model.*;
import br.com.pc.omniflow.domain.repository.NfeXmlRepository;
import br.com.pc.omniflow.dto.nfe.*;
import br.com.pc.omniflow.service.BaseService;
import br.com.pc.omniflow.service.cadastro.EntidadeService;
import br.com.pc.omniflow.service.cadastro.ProdutoEanService;
import br.com.pc.omniflow.service.cadastro.ProdutoFornecedorService;
import br.com.pc.omniflow.service.cadastro.ProdutoService;
import br.com.pc.omniflow.service.fiscal.CfopRegraService;
import br.com.pc.omniflow.service.fiscal.NfeCabecalhoService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NfeParserService extends BaseService {

    private final NfeXmlRepository nfeXmlRepository;
    private final XmlMapper xmlMapper;
    private final EntidadeService entidadeService;
    private final ProdutoFornecedorService produtoFornecedorService;
    private final ProdutoService produtoService;
    private final NfeCabecalhoService nfeCabecalhoService;
    private final CfopRegraService cfopRegraService;
    private final ProdutoEanService produtoEanService;


    public NfeParserService(NfeXmlRepository nfeXmlRepository, XmlMapper xmlMapper, EntidadeService entidadeService,
                            ProdutoFornecedorService produtoFornecedorService, ProdutoService produtoService,
                            NfeCabecalhoService nfeCabecalhoService, CfopRegraService cfopRegraService, ProdutoEanService produtoEanService) {
        this.nfeXmlRepository = nfeXmlRepository;
        this.xmlMapper = xmlMapper;
        this.entidadeService = entidadeService;
        this.produtoFornecedorService = produtoFornecedorService;
        this.produtoService = produtoService;
        this.nfeCabecalhoService = nfeCabecalhoService;
        this.cfopRegraService = cfopRegraService;
        this.produtoEanService = produtoEanService;
    }

    /**
     * Busca todos os XMLs pendentes e processa um a um.
     */
    public void processarPendentes(Long gruId) {
        List<NfeXml> pendentes = comFiltro(gruId, () ->
                nfeXmlRepository.findByStatusProcessamento(StatusProcessamento.RECEBIDO));

        log.info(this.getClass(), "Encontrados " + pendentes.size() + " XMLs para processar.");

        for (NfeXml nfeXml : pendentes) {
            try {
                StatusProcessamento status = processarConteudoXml(gruId, nfeXml);
                nfeXml.setStatusProcessamento(status);
                nfeXmlRepository.save(nfeXml);
            } catch (Exception e) {
                e.printStackTrace();
                nfeXml.setStatusProcessamento(StatusProcessamento.ERRO);
                nfeXml.setLogErro(e.getMessage().length() > 200 ? e.getMessage().substring(0, 200): e.getMessage());
                nfeXmlRepository.save(nfeXml);
                lancarErro("Falha ao processar ID: " + nfeXml.getId() + " e a chave: " + nfeXml.getChaveAcesso(), e);
            }

        }
    }

    private StatusProcessamento processarConteudoXml(Long gruId, NfeXml nfeXml) throws Exception {
        if (nfeXml.getTipoXml() != TipoXml.NFE) {
            String mensagem  = "XML do tipo " + nfeXml.getTipoXml() + " identificado. Lógica de processamento ainda não implementada.";
            nfeXml.setLogErro(mensagem);
            log.info(this.getClass(), mensagem);
            return StatusProcessamento.IGNORADO;
        }

        NfeProcDTO nfeDto = xmlMapper.readValue(nfeXml.getXmlOriginal(), NfeProcDTO.class);
        InfNfeDTO inf = nfeDto.getNfe().getInfNFe();
        IdeDTO ide = inf.getIde();
        StatusProcessamento status = StatusProcessamento.VALIDADO;

        Entidade emitente = entidadeService.buscarOuCriarPorDocumento(gruId,inf.getEmitente());
        Entidade destino = entidadeService.buscarOuCriarPorDocumento(gruId,inf.getDestinatario());

        NfeCabecalho cabecalho = new NfeCabecalho(gruId);
        cabecalho.setNfeXml(nfeXml);
        cabecalho.setEmitente(emitente);
        cabecalho.setDestinatario(destino);
        cabecalho.setDataEmissao(ide.getDataEmissao());
        cabecalho.setModelo(ide.getModeloEnum());
        cabecalho.setNatureza(ide.getNaturezaOperacao());
        cabecalho.setNumeroNota(Integer.parseInt(ide.getNumero()));
        cabecalho.setSerie(Integer.parseInt(ide.getSerie()));
        cabecalho.setTipoOperacao(ide.getTipoOperacaoEnum());

        // --- ETAPA 2: TRATAR PRODUTOS E DE-PARA ---
        for (DetDTO det : inf.getItens()) {
            ProdDTO prodDto = det.getProduto();

            boolean isNotaPropria = entidadeService.isMinhaFilial(gruId, emitente.getDocumento());
            if (isNotaPropria) {
                log.info(this.getClass(), "Processando NOTA PRÓPRIA (Saída/Emissão)");
                // Aqui o CFOP 5102 será SUBTRAÇÃO (-)
            } else {
                log.info(this.getClass(), "Processando NOTA DE TERCEIRO (Entrada/Compra)");
                // Aqui o CFOP 5102 (venda do fornecedor) será SOMA (+) para o seu estoque
            }

            Produto produtoInterno = null;

            if (isNotaPropria){
                produtoInterno = produtoService.buscarOuCriar(gruId, prodDto.getCodigo(), prodDto.getDescricao());
                if (prodDto.getEan() != null && !prodDto.getEan().isBlank()) {
                    produtoEanService.buscarOuSalvarEan(gruId, produtoInterno, prodDto.getEan());
                }
//                produtoInterno.addEan(converterEan(gruId, prodDto, produtoInterno));
            }else{
                produtoInterno = produtoEanService.buscarPorEan(gruId, prodDto.getEan())
                        .map(ProdutoEan::getProduto)
                        .orElse(null);

                /*
                * Acho que aqui deveria ter uma tabela de produto_ean onde armazenaria ai quando o emitente
                * for um Externo ele tem que realizar o cadastro do fornecedor e buscar pelo ean o Produto para
                * fazer o vinculo e caso não encontrar ele pode gravar null na tabela do vinculo de produtoInterno onde
                * terá que ajustar esse vinculo depois. Antes de realizar o calculo do estoque.
                */
                if(produtoInterno == null ){
                    status = StatusProcessamento.PENDENTE_REGRA;
                }
                produtoFornecedorService.buscarOuCriarVinculo(gruId, emitente, produtoInterno, prodDto.getCodigo());
            }

            if (prodDto.getRastros() != null && !prodDto.getRastros().isEmpty()) {
                for (RastroDTO rastro : prodDto.getRastros()) {
                    NfeItem itemLote = converterItem(gruId, det, produtoInterno, isNotaPropria);

                    itemLote.setLote(rastro.getNumeroLote());
                    itemLote.setQuantidade(rastro.getQuantidadeLote());

                    if (rastro.getDataFabricacao() != null)
                        itemLote.setFabricacao(LocalDate.parse(rastro.getDataFabricacao()));
                    if (rastro.getDataValidade() != null)
                        itemLote.setValidade(LocalDate.parse(rastro.getDataValidade()));

                    cabecalho.adicionarItem(itemLote);
                }
            } else {
                NfeItem itemUnico = converterItem(gruId, det, produtoInterno, isNotaPropria);
                itemUnico.setLote("ND");
                cabecalho.adicionarItem(itemUnico);
            }

            if (cfopRegraService.buscarPendente(gruId, det.getProduto().getCfop()) != null){
                status = StatusProcessamento.PENDENTE_REGRA;
            }

        }

        if (inf.getTotal() != null && inf.getTotal().getIcmsTot() != null) {
            NfeTotais totaisEntidade = converterTotais(gruId, inf.getTotal().getIcmsTot());
            cabecalho.setTotais(totaisEntidade);
        }

        nfeCabecalhoService.salvar(gruId, cabecalho);
        return status;
    }

    private NfeItem converterItem(Long gruId, DetDTO det, Produto produtoInterno, boolean isNotaPropria) {
        NfeItem item = new NfeItem(gruId);
        ProdDTO p = det.getProduto();

//        Cfop cfop = cfopService.buscarOuSalvar(prodDto.getCfop())
        CfopRegra cfopRegra = cfopRegraService.buscarOuCriarRegra(gruId, p.getCfop());
        TipoMovimentoEstoque tipoMovimentoEstoque = cfopRegraService.calcularSinal(
                cfopRegra.getCfopString(), isNotaPropria,cfopRegra.isMovimentaEstoque());

        item.setNumeroItem(det.getNumeroItem());
        item.setProduto(produtoInterno); // Pode ser null se não houver vínculo
        item.setCodigoXml(p.getCodigo());
        item.setDescricaoXml(p.getDescricao().length() > 130 ? p.getDescricao().substring(0, 130) : p.getDescricao());
        item.setEan(p.getEan());
        item.setCfopRegra(cfopRegra);
        item.setUnidade(p.getUnidade());
        item.setQuantidade(p.getQuantidade());
        item.setValorUnitario(p.getValorUnitario());
        item.setNcm(p.getNcm());
        item.setSinalUtilizado(tipoMovimentoEstoque);
//        item.setValorTotal(p.getValorProd());
        return item;
    }

    private NfeTotais converterTotais(Long gruId, IcmstotDTO dto) {
        NfeTotais t = new NfeTotais(gruId);
        t.setBaseCalculoIcms(dto.getValorBaseCalculo());
        t.setValorIcms(dto.getValorIcms());
        t.setValorIcmsDeson(dto.getValorIcmsDeson());
        t.setValorFcp(dto.getValorFcp());
        t.setBaseCalculoIcmsSt(dto.getValorBaseCalculoSt());
        t.setValorIcmsSt(dto.getValorIcmsSt());
        t.setValorFcpSt(dto.getValorFcpSt());
        t.setValorFcpStRet(dto.getValorFcpStRet());
        t.setValorProdutos(dto.getValorProdutos());
        t.setValorFrete(dto.getValorFrete());
        t.setValorSeguro(dto.getValorSeguro());
        t.setValorDesconto(dto.getValorDesconto());
        t.setValorIi(dto.getValorImpostoImportacao());
        t.setValorIpi(dto.getValorIpi());
        t.setValorIpiDevol(dto.getValorIpiDevol());
        t.setValorPis(dto.getValorPis());
        t.setValorCofins(dto.getValorCofins());
        t.setOutrasDespesas(dto.getValorOutrasDespesas());
        t.setValorNota(dto.getValorTotalNota());
        t.setValorTotalTributos(dto.getValorTotalTributos());
        return t;
    }

    private ProdutoEan converterEan(Long gruId, ProdDTO prodDto, Produto produto) {
        ProdutoEan produtoEan = new ProdutoEan(gruId);
        produtoEan.setEan(prodDto.getEan());
        produtoEan.setProduto(produto);
        return produtoEan;
    }



}
