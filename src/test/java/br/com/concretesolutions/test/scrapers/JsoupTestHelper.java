/*
* Copyright 2016 Redecard S.A.
*************************************************************
*Nome     : JsoupTestHelper.java
*Autor    : jfelipesp
*Data     : 5 de fev de 2016
*Empresa  : Concrete Solutions / Redecard
*************************************************************
*/
package br.com.concretesolutions.test.scrapers;

import br.com.concretesolutions.scrapers.CepScraper;
import br.com.concretesolutions.scrapers.SroScraper;

import com.google.common.base.Charsets;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.io.File;
import java.io.IOException;

/**
 * @author jfelipesp
 *
 */
public class JsoupTestHelper {


  public static void prepareMock(Class<?> klass, final String file) throws IOException {
    Document document = Jsoup.parse(
        new File(Thread.currentThread().getContextClassLoader().getResource(file).getFile()), Charsets.UTF_8.name());
    Connection connection = Mockito.mock(Connection.class);
    PowerMockito.mockStatic(Jsoup.class);
    PowerMockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);

    if (klass == CepScraper.class) {
      PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString()))
          .thenReturn(connection)
          .thenReturn(connection);
      PowerMockito.when(connection.post()).thenReturn(document);
    } else if (klass == SroScraper.class) {
      PowerMockito.when(connection.data(Mockito.anyString(), Mockito.anyString()))
          .thenReturn(connection)
          .thenReturn(connection)
          .thenReturn(connection);
      PowerMockito.when(connection.get()).thenReturn(document);
    }
  }
}
