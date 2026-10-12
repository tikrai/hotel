package com.gmail.tikrai.hotel;

import static java.util.stream.Collectors.joining;

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

  public ProfitStat profitStat() {
    return new ProfitStat(number, guests.size(), guests.size() * price);
  }

  public int getNumber() {
    return number;
  }

  public double getPrice() {
    return price;
  }

  public String history() {
    String guestList = this.guests.stream().map(Guest::toString).collect(joining("\n"));
    return "Guest history of room #" + this.number + ":\n" +
        guestList + 
        "\n======\nRoom is " + (isAvailable() ? "available" : "occupied");
  }

  public static class BusinessRoom extends Room {

    public BusinessRoom(int number, double price) {
      super(number, price * 1.5);
    }
  }

  public record ProfitStat(
      Integer number,
      int occupiedCount,
      double profit
  ) implements Comparable<ProfitStat> {
    @Override
    public String toString() {
      return "#" + number + " occupiedCount = " + occupiedCount + ", profit = " + profit;
    }

    @Override
    public int compareTo(ProfitStat o) {
      return this.profit == o.profit
          ? this.number.compareTo(o.number)
          : this.profit > o.profit ? -1 : 1;
    }
  }
}
