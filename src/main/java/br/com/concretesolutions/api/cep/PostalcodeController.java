package br.com.concretesolutions.api.cep;

import br.com.concretesolutions.api.models.CepResult;
import br.com.concretesolutions.scrapers.CepScraper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.validation.constraints.Pattern;


/**
 * The Class PostalcodeController.
 *
 * @author jfelipesp
 */
@RestController
public class PostalcodeController {


  @RequestMapping(value = "/cep", method = RequestMethod.GET)
  public ResponseEntity<CepResult> getTracking(@Pattern(regexp = "[0-9]{8}",
      message = "Código inválido") @RequestParam(value = "postalcode", required = true) String postalcode) {
    try {
      return new ResponseEntity<CepResult>(CepScraper.getPostalcodeResult(postalcode), HttpStatus.OK);
    } catch (IllegalStateException e) {
      return new ResponseEntity<CepResult>(HttpStatus.NOT_FOUND);
    } catch (IOException e) {
      return new ResponseEntity<CepResult>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
