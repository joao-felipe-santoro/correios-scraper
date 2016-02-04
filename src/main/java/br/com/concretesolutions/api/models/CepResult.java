package br.com.concretesolutions.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class CepResult.
 *
 * @author jfelipesp
 */
@JsonInclude(Include.NON_NULL)
public class CepResult {

  /** The street. */
  private final String street;
  
  /** The number range. */
  private final String numberRange;
  
  /** The district. */
  private final String district;
  
  /** The city. */
  private final String city; 
  
  /** The state. */
  private final String state;
  
  /** The postal code. */
  private final String postalCode;

  public CepResult(String street, String numberRange, String district, String city, String state, String postalCode) {
    this.street = street;
    this.numberRange = numberRange;
    this.district = district;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
  }

  public String getStreet() {
    return street;
  }

  public String getNumberRange() {
    return numberRange;
  }

  public String getDistrict() {
    return district;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }
  
}
