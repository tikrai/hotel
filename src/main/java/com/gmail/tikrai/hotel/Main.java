package com.gmail.tikrai.hotel;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);
  private static final Random random = new Random();

  public static List<Action> actions = List.of(
      new Action(1, "Guest registration", Hotel::checkin),
      new Action(2, "Check-out of the guest", Hotel::checkout),
      new Action(3, "Review of room availability", Hotel::availability),
      new Action(4, "Room occupancy history and status", Hotel::roomHistory),
      new Action(5, "Room occupancy report", Hotel::occupancyReport),
      new Action(0, "Exit application", h -> ""));

  public static void main(String[] args) {
    HotelProperties props = HotelProperties.load();
    System.out.println("=== Hotel Management System with " + props.roomCount() +
        " rooms with base price of " + props.basePrice() + " EUR ===");

    Hotel hotel = Hotel.initiate(props, scanner, random);

    boolean running = true;

    while (running) {
      printMenu();
      if (scanner.hasNextInt()) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
          System.out.println("Closing hotel. Goodbye!");
          running = false;
        } else {
          Action action = handleChoice(choice);
          System.out.println("\n" + action.name() + " option selected");
          System.out.println(action.action().apply(hotel));
        }
      } else {
        System.out.println("\nInvalid option selected");
        scanner.next();
      }
    }
    scanner.close();
  }

  public static void printMenu() {
    System.out.println("\nPlease select an option:");
    actions.forEach(a -> System.out.println(a.id() + ". " + a.name()));
    System.out.print("Enter your choice: ");
  }

  public static Action handleChoice(int choice) {
    return actions.stream()
        .filter(action -> action.id() == choice)
        .findAny()
        .orElseGet(() -> new Action(choice, "Invalid", h -> ""));
  }
}
