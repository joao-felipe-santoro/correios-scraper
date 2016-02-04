package br.com.concretesolutions.scrapers;

import br.com.concretesolutions.api.models.CepResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * The Class SroScraper.
 *
 * @author jfelipesp
 */
public class CepScraper {

  /** The Constant NOT_FOUND. */
  private static final String NOT_FOUND = "DADOS NAO ENCONTRADOS";

  /** The Constant BASE_URL. */
  private static final String BASE_URL =
      "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";

  /**
   * Gets the tracking result.
   *
   * @param cep the cep
   * @return 
   * @return the tracking result
   * @throws IllegalStateException, IOException, MalformedURLException 
   */
  public static CepResult getPostalcodeResult(final String postalcode)
      throws IllegalStateException, IOException, MalformedURLException {
    // parsing result
    
    
    Document doc = Jsoup.connect(BASE_URL)
                        .data("tipoCEP", "ALL")
                        .data("relaxation", postalcode)
                        .post();

    if (doc.getElementsMatchingText(NOT_FOUND).size() > 0) {
      throw new IllegalStateException();
    }

    Elements tables = doc.getElementsByClass("tmptabela");
    Element table = tables.get(0);
    Elements trs = table.getElementsByTag("tr");
    Elements tds = trs.get(1).getElementsByTag("td");

    return new CepResult(tds.get(0).text().split(" - ")[0], //
        tds.get(0).text().split(" - ").length > 1 ? tds.get(0).text().split(" - ")[1] : null, //
        tds.get(1).text(), //
        tds.get(2).text().split("/")[0], //
        tds.get(2).text().split("/")[1], //
        tds.get(3).text().replace("-", ""));
  }
  
}
