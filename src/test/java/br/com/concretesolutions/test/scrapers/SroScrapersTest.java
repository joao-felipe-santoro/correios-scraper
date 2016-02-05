/*
* Copyright 2016 Redecard S.A.
*************************************************************
*Nome     : SroScrapersTest.java
*Autor    : jfelipesp
*Data     : 04/02/2016
*Empresa  : Concrete Solutions / Redecard
*************************************************************
*/
package br.com.concretesolutions.test.scrapers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import br.com.concretesolutions.api.models.TrackingResult;
import br.com.concretesolutions.scrapers.SroScraper;

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
@PrepareForTest({ Jsoup.class })
public class SroScrapersTest {
  
  /**
   * Tracking number not found test.
   *
   * @throws Exception the exception
   */
  @Test
  public void trackingNumberFoundTest() throws Exception {

    JsoupTestHelper.prepareMock(SroScraper.class, "sro_ok.html");

    TrackingResult result = SroScraper.getTrackingResult("AA123456789AA");
    assertEquals(6, result.getTracking().size());
    assertEquals(result.getTrackingNumber(), "AA123456789AA");

  }

  @Test
  public void trackingNumberNotFoundTest() throws Exception {

    JsoupTestHelper.prepareMock(SroScraper.class, "sro_notFound.html");
    
    try {
      SroScraper.getTrackingResult("AA123456789AA");
      fail("should not reach here!");
    } catch (IllegalStateException ex) {
      // Success \o/
    }
  }

}
