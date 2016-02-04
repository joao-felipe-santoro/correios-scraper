package br.com.concretesolutions.test.scrapers;

import static org.junit.Assert.assertEquals;

import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.scrapers.CepScraper;

import com.google.common.base.Charsets;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

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
  @Test(expected = IllegalStateException.class)
  public void postalcodeNotFoundTest() throws Exception {
    
    final File file = new File(Thread.currentThread().getContextClassLoader().getResource("cep_notFound.html").getFile());
    Connection connection = Mockito.mock(Connection.class);
    Document document = Jsoup.parse(file, Charsets.UTF_8.name());
    PowerMockito.mockStatic(Jsoup.class);    
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
    PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString()))
    .thenReturn(connection)
    .thenReturn(connection);
    PowerMockito.when(connection.post()).thenReturn(document);
    
    CepScraper.getPostalcodeResult("99999999");
  }

  /**
   * Postalcode test.
   *
   * @throws Exception the exception
   */
  @Test
  public void postalcodeTest() throws Exception {
    final File file = new File(Thread.currentThread().getContextClassLoader().getResource("cep_ok.html").getFile());
    Connection connection = Mockito.mock(Connection.class);
    Document document = Jsoup.parse(file, Charsets.UTF_8.name());
    PowerMockito.mockStatic(Jsoup.class);    
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
    PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString()))
    .thenReturn(connection)
    .thenReturn(connection);
    PowerMockito.when(connection.post()).thenReturn(document); 
    
    CepResult result = CepScraper.getPostalcodeResult("04564001");
    
    assertEquals("Rua Pensilvânia", result.getStreet());
    assertEquals("de 421/422 a 599/600 ", result.getNumberRange());
    assertEquals("Cidade Monções ", result.getDistrict());
    assertEquals("São Paulo", result.getCity());
    assertEquals("SP", result.getState());
    assertEquals("04564001", result.getPostalCode());
   
  }

}
