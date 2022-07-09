package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.controller;

import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService service;

    @PostMapping
    public ResponseEntity<VendedorResponseDTO> post(@RequestBody VendedorRequestDTO request) throws URISyntaxException {
         VendedorResponseDTO response = service.insert(request);
         return ResponseEntity.created(new URI("/vendedor/" + response.getId())).body(response);
    }

}
