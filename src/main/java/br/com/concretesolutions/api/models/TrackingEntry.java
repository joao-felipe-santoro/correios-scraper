package br.com.concretesolutions.api.models;

import br.com.concretesolutions.serializers.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingEntry.
 */
@JsonInclude(Include.NON_NULL)
public class TrackingEntry {

  /** The date. */
  @JsonSerialize(using=JsonDateSerializer.class)
  private final Date date;

  /** The location. */
  private final String location;

  /** The action. */
  private final String action;

  /** The detail. */
  private String detail;

  /**
   * Instantiates a new tracking entry.
   *
   * @param date the date
   * @param location the location
   * @param action the action
   */
  public TrackingEntry(final Date date, final String location, final String action) {
    
    this.date = date;
    this.location = location;
    this.action = action;
  }

  /**
   * Gets the date.
   *
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Gets the location.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets the action.
   *
   * @return the action
   */
  public String getAction() {
    return action;
  }
  
  /**
   * Sets the detail.
   *
   * @param detail the new detail
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * Gets the detail.
   *
   * @return the detail
   */
  public String getDetail() {
    return detail;
  }

}
