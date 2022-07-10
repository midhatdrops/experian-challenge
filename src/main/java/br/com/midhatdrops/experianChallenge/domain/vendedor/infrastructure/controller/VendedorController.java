package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.controller;

import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import br.com.midhatdrops.experianChallenge.domain.vendedor.service.VendedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Cacheable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vendedor")
@Slf4j
public class VendedorController {

    @Autowired
    private VendedorService service;

    @PostMapping
    public ResponseEntity<VendedorRequestDTO> post(@RequestBody VendedorRequestDTO request) throws URISyntaxException {
        try {
            VendedorRequestDTO response = service.insert(request);
            return ResponseEntity.created(new URI("/vendedor/" + response.getId())).body(response);
        } catch (MalformedCellphoneException exception) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e ) {
            return ResponseEntity.internalServerError().body(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<VendedorResponseDTO>> getAll(@RequestParam(name = "page",required = false) Integer page) {
        if(page == null ) {
            page = 0;
        }
        List<VendedorResponseDTO> list =  service.getAll(page);
        if(list.size() == 0 ) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponseDTO> getOne(@PathVariable(name = "id") Long id) {
        try {
             VendedorResponseDTO response = service.getOne(id);
             return ResponseEntity.ok().body(response);
        } catch (NoSuchElementException e) {
            log.error("No Such element: " + id);
            return ResponseEntity.noContent().build();
        }
    }

}
