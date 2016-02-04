package br.com.concretesolutions.api.sro;

import br.com.concretesolutions.api.models.TrackingResult;
import br.com.concretesolutions.scrapers.SroScraper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

import javax.validation.constraints.Pattern;


/**
 * @author jfelipesp
 *
 */
@RestController
public class TrackingController {

  @RequestMapping(value = "/sro", method = RequestMethod.GET)
  public ResponseEntity<TrackingResult> getTracking(@Pattern(regexp = "[A-Za-z]{2}[0-9]{9}[A-Za-z]{2}",
      message = "Código inválido") @RequestParam(value = "trackingNumber", required = true) String trackingNumber) {
    try {
      return new ResponseEntity<TrackingResult>(SroScraper.getTrackingResult(trackingNumber), HttpStatus.OK);
    } catch (IllegalStateException e) {
      return new ResponseEntity<TrackingResult>(HttpStatus.NOT_FOUND);
    } catch (ParseException |IOException  e) {
      return new ResponseEntity<TrackingResult>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
