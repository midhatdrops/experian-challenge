package br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.controller;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import br.com.midhatdrops.experianChallenge.domain.vendedor.service.VendedorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class VendedorControllerTest {

    @Autowired
    private VendedorController controller;

    @MockBean
    private VendedorService service;

    private final VendedorRequestDTO  requestDTO = new VendedorRequestDTO(null,"Osvaldo","11 9a76065151",22,"São Paulo", StateEnums.SP,"Sudeste");

    private ResponseEntity<Object> noContent;

    private ResponseEntity<Object> internalError;

    @BeforeEach
    void setup() {
        noContent = ResponseEntity.noContent().build();
        internalError = ResponseEntity.internalServerError().body(null);
    }



    @Test
    void shouldReturnBadContent()  {
        when(service.insert(requestDTO)).thenThrow(MalformedCellphoneException.class);
         ResponseEntity<Object> body = ResponseEntity.badRequest().body(null);
         assertEquals(body,controller.post(requestDTO));
    }

    @Test
    void shouldReturnInternalError()   {
        when(service.insert(requestDTO)).thenThrow(RuntimeException.class);
         ResponseEntity<Object> body = ResponseEntity.internalServerError().body(null);
         assertEquals(body,controller.post(requestDTO));
    }

    @Test
    void shouldReturnNoContentWhenNotHavingAList() {
        when(service.getAll(0)).thenReturn(Collections.emptyList());
         assertEquals(noContent,controller.getAll(0));
    }

    @Test
    void shouldReturnNoContentWhenNotHavingVendedor() {
        when(service.getOne(1L)).thenThrow(NoSuchElementException.class);
         assertEquals(noContent,controller.getOne(1L));
    }
}
