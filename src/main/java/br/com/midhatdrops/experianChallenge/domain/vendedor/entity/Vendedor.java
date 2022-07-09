package br.com.midhatdrops.experianChallenge.domain.vendedor.entity;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;

import javax.persistence.*;

@Entity
@Table(name = "tb_vendor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cellphone;
    private Integer age;
    private String city;
    private StateEnums state;
    private String region;


    public Vendedor(final String name, final String cellphone, final Integer age, final String city, final StateEnums state, final String region) {
        this.name = name;
        if(validateCellphone(cellphone)) {
            this.cellphone = cellphone;
        } else {
            throw new MalformedCellphoneException();
        }
        this.age = age;
        this.city = city;
        this.state = state;
        this.region = region;
    }

    public Vendedor() {
    }


    private boolean validateCellphone(String cellphone) {
        return cellphone.matches("[0-9]{2}[9][0-9]{4,11}") && cellphone.length() == 11;
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

    public Long getId() {
        return id;
    }
}
