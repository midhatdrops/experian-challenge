package br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.controller;

import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.service.AtuacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/atuacao")
@Slf4j
public class AtuacaoController {

    @Autowired
    private AtuacaoService service;

    @PostMapping
    public ResponseEntity<AtuacaoResponseDTO> create(@RequestBody AtuacaoRequestDTO requestDTO) {
        try {
            AtuacaoResponseDTO saved = service.insert(requestDTO);
            return ResponseEntity.created(new URI("/atuacao/"+saved.getRegiao())).body(saved);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
