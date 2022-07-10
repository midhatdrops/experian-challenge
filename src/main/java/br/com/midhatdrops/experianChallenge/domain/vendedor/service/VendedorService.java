package br.com.midhatdrops.experianChallenge.domain.vendedor.service;

import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;

import java.util.List;

public interface VendedorService {

     VendedorRequestDTO insert(VendedorRequestDTO requestDTO);

     List<VendedorResponseDTO> getAll(Integer page);

     VendedorResponseDTO getOne(Long id);
}
