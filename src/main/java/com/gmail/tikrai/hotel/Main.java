package com.gmail.tikrai.hotel;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static List<Action> actions = Map.of(
      1, "Guest registration",
      2, "Check-out of the guest",
      3, "Review of room availability",
      4, "Room occupancy history and status",
      5, "Room occupancy report",
      0, "Exit application"
  ).entrySet().stream().map(e -> new Action(e.getKey(), e.getValue())).toList();

  public static void main(String[] args) {
    HotelProperties props = HotelProperties.load();
    System.out.println("=== Hotel Management System with " + props.roomCount() +
        " rooms with base price of " + props.basePrice() + " EUR ===");

    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      printMenu(actions);
      if (scanner.hasNextInt()) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
          System.out.println("Exiting application. Goodbye!");
          running = false;
        } else {
          System.out.println(handleChoice(actions, choice));
        }
      } else {
        System.out.println("Error: Please enter a valid number.");
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

  public static String handleChoice(List<Action> actions, int choice) {
    Action chosen = actions.stream()
        .filter(action -> action.id() == choice)
        .findAny()
        .orElseGet(() -> new Action(
            choice, "Invalid choice. Please enter a number between 0 and 5"));
    return "Option selected - " + chosen.name();
  }
}
