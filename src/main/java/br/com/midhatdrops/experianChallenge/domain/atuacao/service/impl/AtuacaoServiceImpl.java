package br.com.midhatdrops.experianChallenge.domain.atuacao.service.impl;

import br.com.midhatdrops.experianChallenge.domain.atuacao.entity.Atuacao;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.repository.AtuacaoRepository;
import br.com.midhatdrops.experianChallenge.domain.atuacao.service.AtuacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AtuacaoServiceImpl implements AtuacaoService {

    @Autowired
    private AtuacaoRepository repository;


    @CacheEvict(value = "vendedors",allEntries = true)
    public AtuacaoResponseDTO insert(AtuacaoRequestDTO requestDTO) {
        final Atuacao atuacao = new Atuacao(requestDTO);
        log.info("Atuacao will be saved!" + atuacao);
        Atuacao save = repository.save(atuacao);
         return AtuacaoResponseDTO.builder()
                 .estados(save.getStates())
                 .regiao(save.getRegion())
                 .build();
    }
}
