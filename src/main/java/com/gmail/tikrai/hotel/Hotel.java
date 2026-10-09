package com.gmail.tikrai.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Hotel {

  private static final Random random = new Random();

  private List<Room> rooms = new ArrayList<>();

  public static Hotel initiate(HotelProperties props) {
    Hotel hotel = new Hotel();
    hotel.rooms = IntStream.range(1, props.roomCount() + 1)
        .mapToObj(i -> i % 2 != 0
            ? new Room(i, props.basePrice())
            : new Room.BusinessRoom(i, props.basePrice()))
        .toList();
    return hotel;
  }

  public boolean availability() {
    List<String> rooms = this.rooms.stream()
        .filter(room -> !room.isAvailable())
        .map(Room::stat)
        .toList();
    System.out.println(rooms.isEmpty() ? "All rooms are empty" : "Occupied rooms:");
    rooms.forEach(System.out::println);
    return true;
  }

  public boolean checkin(Scanner scanner) {
    Guest guest = readGuest(scanner);
    return this.rooms.stream()
        .filter(Room::isAvailable)
        .sorted((a, b) -> random.nextInt(3) - 1)
        .findAny()
        .map(room -> room.checkIn(guest))
        .isPresent();
  }

  private Guest readGuest(Scanner scanner) {
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

  public boolean checkout(Scanner scanner) {
    int number = readNumber(scanner);
    rooms.get(number - 1).checkOut();
    return true;
  }

  private int readNumber(Scanner scanner) {
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
}
