package br.com.pc.omniflow.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "NFE_CABECALHOS")
public class NfeCabecalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAB_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @ManyToOne
    @JoinColumn(name = "ENT_ID", nullable = false)
    private Entidade emitente;

    @ManyToOne
    @JoinColumn(name = "ENT_ENT_ID", nullable = false)
    private Entidade destinatario;

    @OneToOne
    @JoinColumn(name = "NFE_ID", nullable = false)
    private NfeXml nfeXml;

    @Column(name = "CAB_NUMERO_NOTA")
    private Integer numeroNota;

    @Column(name = "CAB_SERIE")
    private Integer serie;

    @Column(name = "CAB_DATA_EMISSAO")
    private LocalDate dataEmissao;

    @Column(name = "CAB_NATUREZA")
    private String natureza;

    public NfeCabecalho() {}

    // Getters e Setters ... (Omitidos aqui por brevidade, mas devem ser gerados)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Entidade getEmitente() { return emitente; }
    public void setEmitente(Entidade emitente) { this.emitente = emitente; }
    public Entidade getDestinatario() { return destinatario; }
    public void setDestinatario(Entidade destinatario) { this.destinatario = destinatario; }
}