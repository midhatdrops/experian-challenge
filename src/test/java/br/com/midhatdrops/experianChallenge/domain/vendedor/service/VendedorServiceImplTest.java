package br.com.midhatdrops.experianChallenge.domain.vendedor.service;

import br.com.midhatdrops.experianChallenge.domain.atuacao.entity.Atuacao;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.repository.AtuacaoRepository;
import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.repository.VendedorRepository;
import br.com.midhatdrops.experianChallenge.domain.vendedor.service.impl.VendedorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class VendedorServiceImplTest {

    @Autowired
    private VendedorServiceImpl service;

    @MockBean
    private VendedorRepository repository;

    @MockBean
    private AtuacaoRepository atuacaoRepository;


    private final VendedorRequestDTO requestDTO = new VendedorRequestDTO(null,"Carlinhos","11976065151",22,"São Paulo",StateEnums.SP,"Sudeste");

    @Spy
    private final Vendedor newVendedor = new Vendedor(requestDTO);

  @Test
    void shouldCorrectlySaveEntity() {
      when(repository.save(any(Vendedor.class))).thenReturn(new Vendedor(1L,"Carlinhos","11976065151",22,"São Paulo",StateEnums.SP,"Sudeste"));
       VendedorRequestDTO insert = service.insert(requestDTO);
        assertNotNull(insert);
        assertEquals(insert.getAge(),22);
  }

  @Test
  void shouldCorrectlyReturnMalformedNumberException() {
      VendedorRequestDTO vendedorRequestDTO = new VendedorRequestDTO(null, "Carlinhos", "1197065151", 22, "São Paulo", StateEnums.SP, "Sudeste");
      assertThrows(MalformedCellphoneException.class,() -> service.insert(vendedorRequestDTO));
  }


  @Test
    void shouldCorrectlyReturnVendedors() {
       List<Vendedor> vendedors = Arrays.asList(newVendedor);
       PageImpl<Vendedor> vendedorsPage = new PageImpl<>(vendedors, PageRequest.of(0, 10), vendedors.size());
       when(repository.findAll(any(Pageable.class))).thenReturn(vendedorsPage);
       Optional<Atuacao> atuacao = Optional.of(new Atuacao("Sudeste", Collections.emptyList()));
      when(atuacaoRepository.findById(any(String.class))).thenReturn(atuacao);
      when(newVendedor.getCreatedAt()).thenReturn(Date.from(Instant.now()));
       List<VendedorResponseDTO> vendedorDTOS = service.getAll(0);


      assertNotNull(vendedorDTOS);
      assertEquals(1,vendedorDTOS.size());
      assertEquals(0,vendedorDTOS.get(0).getEstados().size());
  }

  @Test
    void shouldCorrectlyReturnOneVendedor() {
      when(repository.findById(1L)).thenReturn(Optional.of(newVendedor));
      Optional<Atuacao> atuacao = Optional.of(new Atuacao("Sudeste", Collections.emptyList()));
      when(atuacaoRepository.findById(any(String.class))).thenReturn(atuacao);
      when(newVendedor.getCreatedAt()).thenReturn(parseDate("10-07-2022"));
       VendedorResponseDTO one = service.getOne(1L);
       assertNotNull(one);
       assertEquals("Carlinhos",one.getNome());
       assertEquals("10/07/2022",one.getDataInclusao());
       assertEquals(0,one.getEstados().size());
  }


  private Date parseDate(String date) {
      try {
         return new SimpleDateFormat("dd-MM-yyyy").parse(date);
      } catch (ParseException e) {
          return null;
      }

  }

}
