package br.com.concretesolutions.test.scrapers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.scrapers.CepScraper;

import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// TODO: Auto-generated Javadoc
/**
 * The Class CepScrapersTest.
 *
 * @author jfelipesp
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class})
public class CepScrapersTest {
  
  /**
   * Postalcode not found test.
   *
   * @throws Exception the exception
   */
  @Test
  public void postalcodeNotFoundTest() throws Exception {
    JsoupTestHelper.prepareMock(CepScraper.class,"cep_notFound.html");
    
    try {
      CepScraper.getPostalcodeResult("99999999");
      fail("should not reach here!");
    } catch (IllegalStateException ex) {
      // Success \o/
    }
  }

  /**
   * Postalcode test.
   *
   * @throws Exception the exception
   */
  @Test
  public void postalcodeTest() throws Exception { 
    JsoupTestHelper.prepareMock(CepScraper.class,"cep_ok.html");
    
    CepResult result = CepScraper.getPostalcodeResult("04564001");
    
    assertEquals("Rua Pensilvânia", result.getStreet());
    assertEquals("de 421/422 a 599/600 ", result.getNumberRange());
    assertEquals("Cidade Monções ", result.getDistrict());
    assertEquals("São Paulo", result.getCity());
    assertEquals("SP", result.getState());
    assertEquals("04564001", result.getPostalCode());
   
  }

}
