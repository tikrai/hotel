package com.gmail.tikrai.hotel;

import java.util.List;
import java.util.Scanner;

public class Main {
  public static Scanner scanner = new Scanner(System.in);
  public static List<Action> actions = List.of(
      new Action(1, "Guest registration", x -> x.checkin(scanner)),
      new Action(2, "Check-out of the guest", x -> x.checkout(scanner)),
      new Action(3, "Review of room availability", Hotel::availability),
      new Action(4, "Room occupancy history and status", x -> {}),
      new Action(5, "Room occupancy report", x -> {}),
      new Action(0, "Exit application", x -> {}));

  public static void main(String[] args) {
    HotelProperties props = HotelProperties.load();
    System.out.println("=== Hotel Management System with " + props.roomCount() +
        " rooms with base price of " + props.basePrice() + " EUR ===");

    Hotel hotel = Hotel.initiate(props);

    boolean running = true;

    while (running) {
      printMenu(actions);
      if (scanner.hasNextInt()) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
          System.out.println("Closing hotel. Goodbye!");
          running = false;
        } else {
          Action action = handleChoice(actions, choice);
          System.out.println("\n" + action.name() + " option selected");
          action.action().accept(hotel);
        }
      } else {
        System.out.println("\nInvalid option selected");
        scanner.next();
      }
    }
    scanner.close();
  }

  public static void printMenu(List<Action> actions) {
    System.out.println("\nPlease select an option:");
    actions.forEach(a -> System.out.println(a.id() + ". " + a.name()));
    System.out.print("Enter your choice: ");
  }

  public static Action handleChoice(List<Action> actions, int choice) {
    return actions.stream()
        .filter(action -> action.id() == choice)
        .findAny()
        .orElseGet(() -> new Action(choice, "Invalid", x -> {}));
  }
}
