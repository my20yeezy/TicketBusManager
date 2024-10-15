package com.jfb.lecture5.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusTicket {
  private String ticketClass;

  private String ticketType;

  private String startDate;

  private String price;

  public String getTicketClass() {
    return ticketClass;
  }

  public String getTicketType() {
    return ticketType;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getPrice() {
    return price;
  }
}
