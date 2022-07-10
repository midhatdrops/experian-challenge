package br.com.midhatdrops.experianChallenge.domain.atuacao.service;

import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoResponseDTO;

public interface AtuacaoService {

     AtuacaoResponseDTO insert(AtuacaoRequestDTO requestDTO);

}
