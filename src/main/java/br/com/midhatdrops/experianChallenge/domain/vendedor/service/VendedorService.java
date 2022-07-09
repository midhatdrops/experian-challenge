package br.com.midhatdrops.experianChallenge.domain.vendedor.service;

import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;

public interface VendedorService {

     VendedorResponseDTO insert(VendedorRequestDTO requestDTO);

}
