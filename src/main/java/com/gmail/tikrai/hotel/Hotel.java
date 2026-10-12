package com.gmail.tikrai.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Hotel {

  private final Scanner scanner;
  private final Random random;
  private List<Room> rooms = new ArrayList<>();

  public Hotel(Scanner scanner, Random random) {
    this.scanner = scanner;
    this.random = random;
  }

  public static Hotel initiate(HotelProperties props, Scanner scanner, Random random) {
    Hotel hotel = new Hotel(scanner, random);
    hotel.rooms = IntStream.range(1, props.roomCount() + 1)
        .mapToObj(i -> i % 2 != 0
            ? new Room(i, props.basePrice())
            : new Room.BusinessRoom(i, props.basePrice()))
        .toList();
    return hotel;
  }

  public String availability() {
    List<String> rooms = this.rooms.stream()
        .filter(room -> !room.isAvailable())
        .map(Room::info)
        .toList();
    return (rooms.isEmpty() ? "All rooms are empty" : "Occupied rooms:\n")
        + String.join("\n", rooms);
  }

  public void checkin() {
    List<Room> rooms = this.rooms.stream().filter(Room::isAvailable).toList();
    if (rooms.isEmpty()) {
      System.out.println("No rooms are available");
    } else {
      Room room = rooms.get(random.nextInt(rooms.size())).checkIn(readGuest());
      System.out.println("Successful checkin in room " + room.info());
    }
  }

  private Guest readGuest() {
    String firstName = "";
    String lastName = "";

    while (firstName.isEmpty()) {
      System.out.print("Enter guest first name: ");
      firstName = scanner.nextLine().trim();
      if (firstName.isEmpty()) {
        System.out.println("Error: First name cannot be empty.");
      }
    }

    while (lastName.isEmpty()) {
      System.out.print("Enter guest last name: ");
      lastName = scanner.nextLine().trim();
      if (lastName.isEmpty()) {
        System.out.println("Error: Last name cannot be empty.");
      }
    }

    return new Guest(firstName, lastName);
  }

  public void checkout() {
    int number = readNumber();
    Guest guest = rooms.get(number - 1).checkOut();
    System.out.println(guest == null
        ? "Room is not occupied"
        : guest + " was successfully checked out");
  }

  private int readNumber() {
    int size = rooms.size();
    while (true) {
      System.out.print("Enter room number to checkout from (1-" + size + "): ");

      if (scanner.hasNextInt()) {
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        if (roomNumber >= 1 && roomNumber <= size) {
          return roomNumber;
        }
      } else {
        scanner.next();
      }
      System.out.print("Invalid input. ");
    }
  }

  public List<Room> getRooms() {
    return rooms;
  }
}
