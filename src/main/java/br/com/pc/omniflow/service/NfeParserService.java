package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.enums.StatusProcessamento;
import br.com.pc.omniflow.domain.enums.TipoEntidade;
import br.com.pc.omniflow.domain.model.*;
import br.com.pc.omniflow.domain.repository.NfeXmlRepository;
import br.com.pc.omniflow.dto.nfe.*;
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
        comFiltro(gruId, () -> {
            // Busca registros com status 'RECEBIDO' para o grupo atual
            List<NfeXml> pendentes = nfeXmlRepository.findByStatusProcessamento(StatusProcessamento.RECEBIDO);

            log.info(this.getClass(), "Encontrados " + pendentes.size() + " XMLs para processar.");

            for (NfeXml nfeXml : pendentes) {
                try {
                    processarConteudoXml(gruId, nfeXml);
                    nfeXml.setStatusProcessamento(StatusProcessamento.VALIDADO);
                } catch (Exception e) {
                    nfeXml.setStatusProcessamento(StatusProcessamento.ERRO);
                    nfeXml.setLogErro(e.getMessage());
                    lancarErro("Falha ao processar ID: " + nfeXml.getId(), e);
                }
                nfeXmlRepository.save(nfeXml);
            }
            return null;
        });
    }

    private void processarConteudoXml(Long gruId, NfeXml nfeXml) throws Exception {
        NfeProcDTO nfeDto = xmlMapper.readValue(nfeXml.getXmlOriginal(), NfeProcDTO.class);
        InfNfeDTO inf = nfeDto.getNfe().getInfNFe();
        IdeDTO ide = inf.getIde();

        // --- ETAPA 1: TRATAR ENTIDADES (Emitente e Destinatário) ---
        // Aqui está faltando ter a UF para realizar o cadastro do Emitente ou Destinatario.
        Entidade emitente = entidadeService.buscarOuCriarPorDocumento(gruId,inf.getEmitente());
        Entidade destino = entidadeService.buscarOuCriarPorDocumento(gruId,inf.getDestinatario());

        NfeCabecalho cabecalho = new NfeCabecalho();
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

            Produto produtoInterno = null;
            if (TipoEntidade.FILIAL.equals(emitente.getTipo())){
                produtoInterno = produtoService.buscarOuCriar(gruId, prodDto.getCodigo(), prodDto.getDescricao());
                produtoEanService.buscarOuSalvarEan(gruId, produtoInterno, prodDto.getEan());
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
                produtoFornecedorService.buscarOuCriarVinculo(gruId, emitente, produtoInterno, prodDto.getCodigo());
            }

            NfeItem nfeItem = converterItem(gruId,det, produtoInterno);
            cabecalho.adicionarItem(nfeItem);

            if (inf.getTotal() != null && inf.getTotal().getIcmsTot() != null) {
                NfeTotais totaisEntidade = converterTotais(gruId, inf.getTotal().getIcmsTot());
                cabecalho.setTotais(totaisEntidade);
            }

            nfeCabecalhoService.salvar(gruId, cabecalho);
        }
    }

    private NfeItem converterItem(Long gruId, DetDTO det, Produto produtoInterno) {
        NfeItem item = new NfeItem(gruId);
        ProdDTO p = det.getProduto();

//        Cfop cfop = cfopService.buscarOuSalvar(prodDto.getCfop())
        CfopRegra cfopRegra = cfopRegraService.buscarOuCriarRegra(gruId, p.getCfop());

        item.setNumeroItem(det.getNumeroItem());
        item.setProduto(produtoInterno); // Pode ser null se não houver vínculo
        item.setCodigoXml(p.getCodigo());
        item.setDescricaoXml(p.getDescricao());
        item.setEan(p.getEan());
        item.setCfopRegra(cfopRegra);
        item.setUnidade(p.getUnidade());
        item.setQuantidade(p.getQuantidade());
        item.setValorUnitario(p.getValorUnitario());
        item.setNcm(p.getNcm());
//        item.setValorTotal(p.getValorProd());
        item.setLote("");
        item.setValidade(LocalDate.now());
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

}
