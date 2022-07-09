package br.com.midhatdrops.experianChallenge.domain.vendedor.service;

import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import org.junit.jupiter.api.BeforeEach;

public class VendedorServiceImplTest {

    private VendedorRequestDTO requestDTO;

    @BeforeEach
    void setup() {
        requestDTO = new VendedorRequestDTO(new Vendedor("Carlinhos","11976065151",22,"São Paulo", StateEnums.SP,"Sudeste"));
    }
}
