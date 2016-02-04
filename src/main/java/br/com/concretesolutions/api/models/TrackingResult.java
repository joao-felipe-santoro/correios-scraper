package br.com.concretesolutions.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

/**
 * @author jfelipesp
 *
 */
@JsonInclude(Include.NON_NULL)
public class TrackingResult {

  private final List<TrackingEntry> tracking;
  
  private final String trackingNumber;

  
  public TrackingResult(final String trackingNumber, final List<TrackingEntry> tracking) {
    this.trackingNumber = trackingNumber;
    this.tracking = tracking;
  }

  public List<TrackingEntry> getTracking() {
    return tracking;
  }


  public String getTrackingNumber() {
    return trackingNumber;
  }
  
}
