package br.com.pc.omniflow.domain.repository;

import br.com.pc.omniflow.domain.model.ProdutoEan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoEanRepository extends JpaRepository<ProdutoEan, Long> {

    Optional<ProdutoEan> findByEan(String ean);

}
