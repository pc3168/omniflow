package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.model.GrupoEmpresa;

public interface TenantEntity {

    void setGrupo(GrupoEmpresa grupo);

    GrupoEmpresa getGrupo();
}
