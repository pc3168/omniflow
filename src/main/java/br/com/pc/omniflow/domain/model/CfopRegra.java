package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.StatusRegraConverter;
import br.com.pc.omniflow.domain.enums.StatusRegra;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CFOP_REGRA", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"REG_CFOP", "GRU_ID"})
})
public class CfopRegra extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REG_CFOP", nullable = false)
    private Cfop cfop; // Relaciona com o código oficial

//    @Convert(converter = TipoMovimentoConverter.class)
//    @Column(name = "REG_SINAL_ESTOQUE", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
//    private TipoMovimentoEstoque sinalEstoque = TipoMovimentoEstoque.NENHUM;

    @Column(name = "REG_MOVIMENTA_ESTOQUE", nullable = false)
    private boolean movimentaEstoque = true;

    @Convert(converter = StatusRegraConverter.class)
    @Column(name = "REG_STATUS", length = 1, nullable = false, columnDefinition = "Char(1) DEFAULT 'P'")
    private StatusRegra status = StatusRegra.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

//    @Column(name = "REG_DESCRICAO", length = 100, nullable = false)
//    private String descricao;

//    @Column(name = "REG_AFETA_ESTOQUE", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
//    private boolean afetaEstoque = true;


    public CfopRegra() {}

    public String getCfopString(){
        return this.cfop.getCodigo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cfop getCfop() {
        return cfop;
    }

    public void setCfop(Cfop cfop) {
        this.cfop = cfop;
    }

    public boolean isMovimentaEstoque() {
        return movimentaEstoque;
    }

    public void setMovimentaEstoque(boolean movimentaEstoque) {
        this.movimentaEstoque = movimentaEstoque;
    }

    public StatusRegra getStatus() {
        return status;
    }

    public void setStatus(StatusRegra status) {
        this.status = status;
    }

    @Override
    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    @Override
    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CfopRegra cfopRegra = (CfopRegra) o;
        return Objects.equals(id, cfopRegra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
