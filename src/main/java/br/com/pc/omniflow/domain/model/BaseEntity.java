package br.com.pc.omniflow.domain.model;

import br.com.pc.omniflow.service.TenantEntity;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@MappedSuperclass
@FilterDef(name = "grupoFilter", parameters = @ParamDef(name = "grupoId", type = Long.class))
@Filter(name = "grupoFilter", condition = "gru_id = :grupoId")
public abstract class BaseEntity implements TenantEntity {

}

