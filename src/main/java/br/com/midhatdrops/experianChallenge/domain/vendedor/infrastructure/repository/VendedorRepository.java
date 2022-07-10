package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.repository;

import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
}
