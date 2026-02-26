package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.StatusRegraConverter;
import br.com.pc.omniflow.converter.TipoMovimentoConverter;
import br.com.pc.omniflow.domain.enums.StatusRegra;
import br.com.pc.omniflow.domain.enums.TipoMovimentoEstoque;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "REGRA_CFOP")
public class RegraCfop extends BaseEntity{

    @Id
    @Column(name = "REG_CFOP", length = 4)
    private String cfop;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "REG_DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Convert(converter = TipoMovimentoConverter.class)
    @Column(name = "REG_SINAL_ESTOQUE", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
    private TipoMovimentoEstoque sinalEstoque = TipoMovimentoEstoque.NENHUM;

    @Column(name = "REG_AFETA_ESTOQUE", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean afetaEstoque = true;

    @Convert(converter = StatusRegraConverter.class)
    @Column(name = "REG_STATUS", length = 1, nullable = false, columnDefinition = "Char(1) DEFAULT 'P'")
    private StatusRegra status = StatusRegra.PENDENTE;

    public RegraCfop() {}

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public GrupoEmpresa getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEmpresa grupo) {
        this.grupo = grupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoMovimentoEstoque getSinalEstoque() {
        return sinalEstoque;
    }

    public void setSinalEstoque(TipoMovimentoEstoque sinalEstoque) {
        this.sinalEstoque = sinalEstoque;
    }

    public boolean isAfetaEstoque() {
        return afetaEstoque;
    }

    public void setAfetaEstoque(boolean afetaEstoque) {
        this.afetaEstoque = afetaEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegraCfop regraCfop = (RegraCfop) o;
        return Objects.equals(cfop, regraCfop.cfop);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cfop);
    }
}
