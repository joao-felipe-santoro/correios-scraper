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
@PrepareForTest({ Jsoup.class })
public class SroScrapersTest {

  /**
   * Tracking number not found test.
   *
   * @throws Exception the exception
   */
  @Test
  public void trackingNumberFoundTest() throws Exception {

    final File file = new File(Thread.currentThread().getContextClassLoader().getResource("sro_ok.html").getFile());
    Connection connection = Mockito.mock(Connection.class);
    Document document = Jsoup.parse(file, Charsets.UTF_8.name());
    PowerMockito.mockStatic(Jsoup.class);
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
    PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString())).thenReturn(connection)
        .thenReturn(connection).thenReturn(connection);
    PowerMockito.when(connection.get()).thenReturn(document);


    TrackingResult result = SroScraper.getTrackingResult("AA123456789AA");
    assertEquals(6, result.getTracking().size());
    assertEquals(result.getTrackingNumber(), "AA123456789AA");

  }

  @Test
  public void trackingNumberNotFoundTest() throws Exception {

    final File file =
        new File(Thread.currentThread().getContextClassLoader().getResource("sro_notFound.html").getFile());
    Connection connection = Mockito.mock(Connection.class);
    Document document = Jsoup.parse(file, Charsets.UTF_8.name());
    PowerMockito.mockStatic(Jsoup.class);
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
    PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString())).thenReturn(connection)
        .thenReturn(connection).thenReturn(connection);
    PowerMockito.when(connection.get()).thenReturn(document);

    try {
      SroScraper.getTrackingResult("AA123456789AA");
      fail("should not reach here!");
    } catch (IllegalStateException ex) {
      // Success \o/
    }
  }

}
