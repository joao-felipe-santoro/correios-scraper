package br.com.concretesolutions.scrapers;

import br.com.concretesolutions.api.models.TrackingEntry;
import br.com.concretesolutions.api.models.TrackingResult;

import com.google.common.collect.Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SroScraper.
 *
 * @author jfelipesp
 */
public class SroScraper {

  /** The Constant NOT_FOUND. */
  private static final String NOT_FOUND = "O nosso sistema não possui dados sobre o objeto informado";

  /** The Constant BASE_URL. */
  private static final String BASE_URL =
      "http://www2.correios.com.br/sistemas/rastreamento/resultado.cfm";

  /** The Constant SDF. */
  private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm");

  /**
   * Gets the tracking result.
   *
   * @param objeto the objeto
   * @return the tracking result
   * @throws ParseException the parse exception
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static TrackingResult getTrackingResult(final String trackingNumber)
      throws IllegalStateException, ParseException, MalformedURLException, IOException {

    Document doc = Jsoup.connect(BASE_URL)
                        .header("Referer", "http://www2.correios.com.br/sistemas/rastreamento/")
                        .data("objetos",trackingNumber)
                        .post();
    
    if (doc.getElementsMatchingText(NOT_FOUND).size() > 0) {
      throw new IllegalStateException();
    }
    // Element DIV contains tracking information
    Element div = doc.getElementsByClass("ctrlcontent").get(0);
    Elements table = div.getElementsByClass("sro");

    List<TrackingEntry> list = new ArrayList<TrackingEntry>();

    Elements trs = table.get(0).getElementsByTag("tr");
    Element tdEvent = null;
    Element tdLabel = null;

    /*
     * first TR is heading, so we skip it TRs can hold a event entry or a more detailed event description
     */
    for (int i = 1; i < trs.size(); i++) {
      tdEvent = trs.get(i).getElementsByClass("sroDtEvent").get(0);
      tdLabel= trs.get(i).getElementsByClass("sroLbEvent").get(0);
      String location = null;
      String date = null;
      if(!tdEvent.getElementsByTag("label").isEmpty()) {
        location = tdEvent.getElementsByTag("label").get(0).text();
        tdEvent.getElementsByTag("label").empty();
        date = tdEvent.text();
      } else {
        String[] splits = tdEvent.html().split("<br>");
        location = splits[2];
        date = splits[0] + splits[1];
      }
      list.add(new TrackingEntry(SDF.parse(date), location, tdLabel.text()));
    }

    return new TrackingResult(trackingNumber, Lists.reverse(list));
  }

}
