package br.com.midhatdrops.experianChallenge.domain.vendedor.entity;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jasypt.util.text.BasicTextEncryptor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_vendor")
@Getter
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

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


    public Vendedor(Long id, final String name, final String cellphone, final Integer age, final String city, final StateEnums state, final String region) {
        this.id = id;
        this.name = name;
        if(validateCellphone(cellphone)) {
            this.cellphone = cellphone;
        } else {
            throw new MalformedCellphoneException(cellphone);
        }
        this.age = age;
        this.city = city;
        this.state = state;
        this.region = region;
    }

    public Vendedor(final VendedorRequestDTO requestDTO) {
        this.id = requestDTO.getId() == null ? null : requestDTO.getId();
        this.name = requestDTO.getName().trim();
        if(validateCellphone(requestDTO.getCellphone())) {
            this.cellphone = requestDTO.getCellphone().trim();
        } else {
            throw new MalformedCellphoneException(requestDTO.getCellphone());
        }
        this.age = requestDTO.getAge();
        this.city = requestDTO.getCity();
        this.state = requestDTO.getState();
        this.region = requestDTO.getRegion().trim();
    }

    public Vendedor() {
    }


    private boolean validateCellphone(String cellphone) {
        return cellphone.matches("[0-9]{2}[9][0-9]{4,11}") && cellphone.length() == 11;
    }


}
