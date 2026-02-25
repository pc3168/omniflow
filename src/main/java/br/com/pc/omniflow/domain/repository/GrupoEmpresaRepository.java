package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.GrupoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoEmpresaRepository extends JpaRepository<GrupoEmpresa, Long> {

}
