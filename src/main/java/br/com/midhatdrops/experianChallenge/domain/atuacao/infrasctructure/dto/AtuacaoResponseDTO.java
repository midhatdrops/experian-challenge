package br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AtuacaoResponseDTO {

    private String regiao;
    private List<StateEnums> estados;


}
