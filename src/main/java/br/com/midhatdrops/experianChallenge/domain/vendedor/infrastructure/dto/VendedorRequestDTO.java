package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto;

import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class VendedorRequestDTO {

    private Long id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("celular")
    private String cellphone;
    @JsonProperty("idade")
    private Integer age;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private StateEnums state;
    @JsonProperty("regiao")
    private String region;

    public VendedorRequestDTO() {
    }

    public VendedorRequestDTO(Vendedor entity) {
        if(entity.getId() != null) {
            this.id = entity.getId();
        }
        this.id = null;
        this.name = entity.getName();
        this.cellphone = entity.getCellphone();
        this.age = entity.getAge();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.region = entity.getRegion();
    }

    public VendedorRequestDTO(final Long id, final String name, final String cellphone, final Integer age, final String city, final StateEnums state, final String region) {
        this.id = id;
        this.name = name;
        this.cellphone = cellphone;
        this.age = age;
        this.city = city;
        this.state = state;
        this.region = region;
    }
}
