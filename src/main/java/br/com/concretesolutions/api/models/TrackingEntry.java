package br.com.concretesolutions.api.models;

import br.com.concretesolutions.serializers.JsonDateSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonInclude(Include.NON_NULL)
public class TrackingEntry {

  @JsonProperty
  @JsonSerialize(using=JsonDateSerializer.class)
  private Date date;

  @JsonProperty
  private String location;

  @JsonProperty
  private String action;

  @JsonProperty
  private String detail;

  public TrackingEntry(Date date, String location, String action) {
    
    this.date = date;
    this.location = location;
    this.action = action;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

}
