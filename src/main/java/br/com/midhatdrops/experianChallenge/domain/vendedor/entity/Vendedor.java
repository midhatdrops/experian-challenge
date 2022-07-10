package br.com.midhatdrops.experianChallenge.domain.vendedor.entity;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "tb_vendor")
@Getter
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String cellphone;

    @NotNull
    @NotBlank
    private Integer age;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private StateEnums state;

    @NotNull
    @NotBlank
    private String region;

    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;


    public Vendedor(Long id, final String name,  String cellphone, final Integer age, final String city, final StateEnums state, final String region) {
        this.id = id;
        this.name = name;
         String trim = cellphone.replace("-", "").replaceAll("\\s","");
        if(validateCellphone(trim)) {
            this.cellphone = cellphone;
        } else {
            throw new MalformedCellphoneException(cellphone);
        }
        this.age = age;
        this.city = city;
        this.state = state;
        this.region = region;
    }

    public Vendedor( VendedorRequestDTO requestDTO) {
        this.id = requestDTO.getId() == null ? null : requestDTO.getId();
        this.name = requestDTO.getName().trim();
        if(validateCellphone(requestDTO.getCellphone())) {
            this.cellphone = requestDTO.getCellphone();
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
