package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto;

import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VendedorRequestDTO {

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
        this.name = entity.getName();
        this.cellphone = entity.getCellphone();
        this.age = entity.getAge();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.region = entity.getRegion();
    }

    public String getName() {
        return name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public StateEnums getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }
}
