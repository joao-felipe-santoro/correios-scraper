package br.com.concretesolutions.test.scrapers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.scrapers.CepScraper;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class CepScrapersTest.
 *
 * @author jfelipesp
 */
public class CepScrapersTest {
  
  /**
   * Postalcode not found test.
   *
   * @throws Exception the exception
   */
  @Test(expected = IllegalStateException.class)
  public void postalcodeNotFoundTest() throws Exception {
    
    CepScraper.getPostalcodeResult("99999999");
  }

  /**
   * Postalcode test.
   *
   * @throws Exception the exception
   */
  @Test
  public void postalcodeTest() throws Exception {
    
    CepResult result = CepScraper.getPostalcodeResult("04564001");
    
    assertEquals("Rua Pensilvânia", result.getStreet());
    assertEquals("de 421/422 a 599/600 ", result.getNumberRange());
    assertEquals("Cidade Monções ", result.getDistrict());
    assertEquals("São Paulo", result.getCity());
    assertEquals("SP", result.getState());
    assertEquals("04564001", result.getPostalCode());
    
    result = CepScraper.getPostalcodeResult("20241320");
    
    assertEquals("Rua Joaquim Murtinho ", result.getStreet());
    assertNull(result.getNumberRange());
    assertEquals("Santa Teresa ", result.getDistrict());
    assertEquals("Rio de Janeiro", result.getCity());
    assertEquals("RJ", result.getState());
    assertEquals("20241320", result.getPostalCode());
  }

}
