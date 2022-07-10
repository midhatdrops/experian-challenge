package br.com.midhatdrops.experianChallenge.domain.atuacao.entity;

import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.dto.AtuacaoRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_atuacao")
@Getter
public class Atuacao {

    @Id
    @Column(unique = true)
    private String region;

    @ElementCollection
    @NotBlank
    @NonNull
    private List<StateEnums> states;

    public Atuacao() {
    }


    public Atuacao(final String region, final List<StateEnums> states) {
        this.region = region;
        this.states = states;
    }

    public Atuacao(AtuacaoRequestDTO requestDTO) {
        this.region = requestDTO.getRegiao();
        this.states = requestDTO.getEstados();
    }
}
