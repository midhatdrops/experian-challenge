package br.com.midhatdrops.experianChallenge.domain.vendedor.entity;

import br.com.midhatdrops.experianChallenge.domain.vendedor.enums.StateEnums;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
public class VendedorTests {

  @Test
    void shouldNotAllowIncorrectCellphone() {
    assertThrows( MalformedCellphoneException.class, () -> new Vendedor("nome","11a321312",22,"cidade", StateEnums.SP,"região"));
  }

  @Test
    void shouldCreateEntityCorrectly() {
     Vendedor vendedor = new Vendedor("nome", "11976065151", 22, "cidade", StateEnums.SP, "região");
    assertEquals("11976065151",  vendedor.getCellphone());
  }
}
