package br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.repository;

import br.com.midhatdrops.experianChallenge.domain.atuacao.entity.Atuacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtuacaoRepository extends JpaRepository<Atuacao,String> {
}
