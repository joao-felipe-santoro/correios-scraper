package br.com.concretesolutions.scrapers;

import br.com.concretesolutions.api.models.TrackingEntry;
import br.com.concretesolutions.api.models.TrackingResult;

import com.google.common.collect.Lists;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class SroScraper.
 *
 * @author jfelipesp
 */
@Service
public class SroScraper {

  /** The Constant NOT_FOUND. */
  private static final String NOT_FOUND = "O nosso sistema não possui dados sobre o objeto informado";

  /** The Constant BASE_URL. */
  private static final String BASE_URL =
      "http://websro.correios.com.br/sro_bin/txect01$.QueryList?P_LINGUA=001&P_TIPO=001&Z_ACTION=Search&P_COD_UNI=%s";

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

    final String url = String.format(BASE_URL, trackingNumber);

    Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);
    if (doc.getElementsMatchingText(NOT_FOUND).size() > 0) {
      throw new IllegalStateException();
    }
    // Element table contains tracking information
    Elements tables = doc.getElementsByTag("table");
    Element table = tables.get(0);

    List<TrackingEntry> list = new ArrayList<TrackingEntry>();

    Elements trs = table.getElementsByTag("tr");
    Elements tds = null;

    /*
     * first TR is heading, so we skip it TRs can hold a event entry or a more detailed event description
     */
    for (int i = 1; i < trs.size(); i++)

    {
      tds = trs.get(i).getElementsByTag("td");
      if (tds.size() == 1) { // a TR with a single TD holds the detailed event description
        list.get(list.size() - 1).setDetail(tds.get(0).text());
      } else { // a TR with three TDs is a event entry it self
               // td 1 = date | td 2 - location | td 3 - action
        list.add(new TrackingEntry(SDF.parse(tds.get(0).text()), tds.get(1).text(), tds.get(2).text()));
      }
    }

    return new TrackingResult(trackingNumber, Lists.reverse(list));
  }

}
