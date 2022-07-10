package br.com.midhatdrops.experianChallenge.domain.atuacao.service;

import br.com.midhatdrops.experianChallenge.domain.atuacao.entity.Atuacao;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.repository.AtuacaoRepository;
import br.com.midhatdrops.experianChallenge.domain.atuacao.service.impl.AtuacaoServiceImpl;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class AtuacaoServiceImplTest {

    @Autowired
    private AtuacaoServiceImpl service;


    @MockBean
    private AtuacaoRepository repository;

    private final AtuacaoRequestDTO  requestDTO = new AtuacaoRequestDTO("sudeste", List.of(StateEnums.DF,StateEnums.CE));

    @Test
    void shouldInsertNewAtuacaoCorrectly() {
        when(repository.save(any(Atuacao.class))).thenReturn(new Atuacao("Sudeste",List.of(StateEnums.DF,StateEnums.CE)));
         AtuacaoResponseDTO savedEntity = service.insert(requestDTO);
         assertNotNull(savedEntity);
         assertEquals("Sudeste",savedEntity.getRegiao());
         assertEquals(StateEnums.DF,savedEntity.getEstados().get(0));
    }


}
