/*
* Copyright 2016 Redecard S.A.
*************************************************************
*Nome     : PostalcocdeControllerTest.java
*Autor    : jfelipesp
*Data     : 5 de fev de 2016
*Empresa  : Concrete Solutions / Redecard
*************************************************************
*/
package br.com.concretesolutions.test.api.cep;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.concretesolutions.api.cep.PostalcodeController;
import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.config.ApplicationConfig;
import br.com.concretesolutions.scrapers.CepScraper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

/**
 * @author jfelipesp
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CepScraper.class})
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
public class PostalcocdeControllerTest {

  @InjectMocks  
  private PostalcodeController controller;
  
  private MockMvc mock;
  
  
  @Before
  public void setUp() {
    initMocks(this);
    mock = MockMvcBuilders.standaloneSetup(controller).build();
    PowerMockito.mockStatic(CepScraper.class);
  }
  
  @Test
  public void postalcodeBadRequestTest() throws Exception {
    mock.perform(get("/cep")).andExpect(status().isBadRequest());
  }
  
  @Test
  public void postalcodeOkTest() throws Exception {
    PowerMockito.when(CepScraper.getPostalcodeResult(Mockito.eq("04564001"))).thenReturn(new CepResult("Unit Test Street", "0 to 100", "PowerMock", "JUnit", "Java", "04564001"));
    
    mock.perform(get("/cep").param("postalcode", "04564001"))
          .andExpect(status().isOk())
          .andExpect(content().string("{"+ //
                                        "\"street\":\"Unit Test Street\","+ //
                                        "\"numberRange\":\"0 to 100\","+ //
                                        "\"district\":\"PowerMock\","+ //
                                        "\"city\":\"JUnit\","+ //
                                        "\"state\":\"Java\","+ //
                                        "\"postalCode\":\"04564001\""+ //
                                      "}"));
  }
  
  @Test
  public void postalcodeNotFoundTest() throws Exception {
    PowerMockito.when(CepScraper.getPostalcodeResult(Mockito.eq("04564002"))).thenThrow(new IllegalStateException());
    
    mock.perform(get("/cep").param("postalcode", "04564002"))
          .andExpect(status().isNotFound())
          .andExpect(content().string(""));
  }
  
  @Test
  public void postalcodeServiceUnavailableTest() throws Exception {
    PowerMockito.when(CepScraper.getPostalcodeResult(Mockito.eq("04564003"))).thenThrow(new IOException());
    
    mock.perform(get("/cep").param("postalcode", "04564003"))
          .andExpect(status().isServiceUnavailable())
          .andExpect(content().string(""));
  }
  
}
