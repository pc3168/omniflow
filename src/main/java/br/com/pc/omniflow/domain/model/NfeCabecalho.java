package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.ModeloDocumentoConverter;
import br.com.pc.omniflow.converter.TipoOperacaoConverter;
import br.com.pc.omniflow.domain.enums.ModeloDocumento;
import br.com.pc.omniflow.domain.enums.TipoOperacao;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "NFE_CABECALHOS")
public class NfeCabecalho extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAB_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "ENT_EMITENTE", nullable = false)
    private Entidade emitente;

    @ManyToOne
    @JoinColumn(name = "ENT_DESTINATARIO", nullable = false)
    private Entidade destinatario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NFE_ID", nullable = false)
    private NfeXml nfeXml;

    @Convert(converter = ModeloDocumentoConverter.class)
    @Column(name = "CAB_MODELO", length = 2, nullable = false)
    private ModeloDocumento modelo;

    @Convert(converter = TipoOperacaoConverter.class)
    @Column(name = "CAB_TIPO_OPERACAO", length = 1, nullable = false)
    private TipoOperacao tipoOperacao; // '0' ou '1'

    @Column(name = "CAB_NUMERO_NOTA")
    private Integer numeroNota;

    @Column(name = "CAB_SERIE")
    private Integer serie;

    @Column(name = "CAB_DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "CAB_NATUREZA")
    private String natureza;

    // CascadeType.ALL: Se salvar o cabecalho, salva os itens. Se excluir, exclui os itens.
    // orphanRemoval = true: Se você remover um item da lista no Java, ele deleta do banco.
    @OneToMany(mappedBy = "cabecalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NfeItem> itens = new ArrayList<>();

    @OneToOne(mappedBy = "cabecalho", cascade = CascadeType.ALL)
    private NfeTotais totais;

    // Métodos auxiliares para manter os dois lados da relação (IMPORTANTE!)
    public void adicionarItem(NfeItem item) {
        itens.add(item);
        item.setCabecalho(this);
    }

    public void setTotais(NfeTotais totais) {
        this.totais = totais;
        totais.setCabecalho(this);
    }

    public NfeCabecalho() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    public Entidade getEmitente() {
        return emitente;
    }

    public void setEmitente(Entidade emitente) {
        this.emitente = emitente;
    }

    public Entidade getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Entidade destinatario) {
        this.destinatario = destinatario;
    }

    public NfeXml getNfeXml() {
        return nfeXml;
    }

    public void setNfeXml(NfeXml nfeXml) {
        this.nfeXml = nfeXml;
    }

    public ModeloDocumento getModelo() {
        return modelo;
    }

    public void setModelo(ModeloDocumento modelo) {
        this.modelo = modelo;
    }

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacao tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public List<NfeItem> getItens() {
        return itens;
    }

    public void setItens(List<NfeItem> itens) {
        this.itens = itens;
    }

    public NfeTotais getTotais() {
        return totais;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NfeCabecalho that = (NfeCabecalho) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}