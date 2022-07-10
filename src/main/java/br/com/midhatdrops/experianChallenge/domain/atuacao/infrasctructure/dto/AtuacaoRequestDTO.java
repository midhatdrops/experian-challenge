package br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtuacaoRequestDTO {

    private String regiao;
    private List<StateEnums> estados;

    public AtuacaoRequestDTO() {
    }

    public AtuacaoRequestDTO(final String regiao, final List<StateEnums> estados) {
        this.regiao = regiao;
        this.estados = estados;
    }
}
