package br.com.concretesolutions.scrapers;

import br.com.concretesolutions.api.models.CepResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Class SroScraper.
 *
 * @author jfelipesp
 */
@Service
public class CepScraper {

  /** The Constant NOT_FOUND. */
  private static final String NOT_FOUND = "DADOS NAO ENCONTRADOS";

  /** The Constant BASE_URL. */
  private static final String BASE_URL =
      "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";
  
  private static final String PARAMETERS = "tipoCEP=ALL&relaxation=%s";

  /**
   * Gets the tracking result.
   *
   * @param cep the cep
   * @return 
   * @return the tracking result
   * @throws IllegalStateException, IOException, MalformedURLException 
   */
  public static CepResult getPostalcodeResult(final String postalcode) throws IllegalStateException, IOException, MalformedURLException {

    final URL url = new URL(BASE_URL);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    //add request header
    con.setRequestMethod("POST");
    final String urlParameters = String.format(PARAMETERS, postalcode);
    
    // Send post request
    con.setDoOutput(true);
    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    wr.writeBytes(urlParameters);
    wr.flush();
    wr.close();

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    
    // parsing result
    Document doc = Jsoup.parse(response.toString());
    
    if(doc.getElementsMatchingText(NOT_FOUND).size() > 0) {
      throw new IllegalStateException();
    }
    
    Elements tables = doc.getElementsByClass("tmptabela");
    Element table = tables.get(0);
    Elements trs = table.getElementsByTag("tr");
    Elements tds = trs.get(1).getElementsByTag("td");
    
    return new CepResult(tds.get(0).text().split(" - ")[0], //
                         tds.get(0).text().split(" - ").length > 1 ? tds.get(0).text().split(" - ")[1] : null, // 
                         tds.get(1).text(),  //
                         tds.get(2).text().split("/")[0], //
                         tds.get(2).text().split("/")[1], // 
                         tds.get(3).text().replace("-", ""));
  }

}
