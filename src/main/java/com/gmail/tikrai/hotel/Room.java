package com.gmail.tikrai.hotel;

import java.util.ArrayList;
import java.util.List;

public class Room {
  private final int number;
  private final double price;
  private Guest guest;
  private final List<Guest> guests = new ArrayList<>();

  public Room(int number, double price) {
    this.number = number;
    this.price = price;
  }

  public boolean isAvailable() {
    return this.guest == null;
  }

  public Room checkIn(Guest guest) {
    this.guest = guest;
    this.guests.add(guest);
    return this;
  }

  public String info() {
    return number + ". " + guest;
  }

  public Guest checkOut() {
    Guest guest = this.guest;
    this.guest = null;
    return guest;
  }

  public static class BusinessRoom extends Room {

    public BusinessRoom(int number, double price) {
      super(number, price * 1.5);
    }
  }
}
