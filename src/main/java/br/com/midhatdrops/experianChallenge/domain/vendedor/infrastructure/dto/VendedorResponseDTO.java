package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class VendedorResponseDTO {


        private String nome;
        private String telefone;
        private String dataInclusao;
        private String estado;
        private List<StateEnums> estados;
        private Integer idade;
        private String cidade;






}
