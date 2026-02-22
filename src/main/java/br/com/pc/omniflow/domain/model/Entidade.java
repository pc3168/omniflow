package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.converter.TipoEntidadeConverter;
import br.com.pc.omniflow.domain.enums.TipoDocumento;
import br.com.pc.omniflow.domain.enums.TipoEntidade;
import jakarta.persistence.*;

@Entity
@Table(name = "ENTIDADES", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_GRUPO_DOCUMENTO",
                columnNames = {"GRU_ID", "ENT_DOCUMENTO"}
        )
})
public class Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GRU_ID", nullable = false)
    private GrupoEmpresa grupo;

    @Column(name = "ENT_DOCUMENTO", length = 14, nullable = false)
    private String documento;

    @Column(name = "ENT_NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "ENT_CODIGO_EXTERNO", length = 10, nullable = false)
    private String codigoExterno;

    @Column(name = "ENT_TIPO_DOCUMENTO", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT '0'")
    private TipoDocumento tipoDocumento = TipoDocumento.CNPJ; // Converterá 0 ou 1

    @Column(name = "ENT_UF", length = 2, nullable = false)
    private String uf;


    // O columnDefinition ajuda o Hibernate a criar o script SQL com DEFAULT 'E'
    @Convert(converter = TipoEntidadeConverter.class)
    @Column(name = "ENT_TIPO", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'E'")
    private TipoEntidade tipo = TipoEntidade.EXTERNO; // F, L, E, T

    public Entidade() {
    }

}
