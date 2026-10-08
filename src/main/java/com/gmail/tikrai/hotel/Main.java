package com.gmail.tikrai.hotel;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    HotelProperties props = HotelProperties.load();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    System.out.println("=== Hotel Management System with " + props.roomCount() +
        " rooms with base price of " + props.basePrice() + " EUR ===");

    while (running) {
      printMenu();
      if (scanner.hasNextInt()) {
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) {
          System.out.println("Exiting application. Goodbye!");
          running = false;
        } else {
          handleChoice(choice);
        }
      } else {
        System.out.println("Error: Please enter a valid number.");
        scanner.next();
      }
    }
    scanner.close();
  }

  public static void printMenu() {
    System.out.println("\nPlease select an option:");
    System.out.println("1. Guest registration");
    System.out.println("2. Check-out of the guest");
    System.out.println("3. Review of room availability");
    System.out.println("4. Room occupancy history and status");
    System.out.println("5. Room occupancy report");
    System.out.println("0. Exit application");
    System.out.print("Enter your choice: ");
  }

  public static String handleChoice(int choice) {
    String optionName;
    switch (choice) {
      case 1:
        optionName = "Guest registration";
        break;
      case 2:
        optionName = "Check-out of the guest";
        break;
      case 3:
        optionName = "Review of room availability";
        break;
      case 4:
        optionName = "Room occupancy history and status";
        break;
      case 5:
        optionName = "Room occupancy report";
        break;
      default:
        String errorMsg = "Invalid choice. Please enter a number between 0 and 5.";
        System.out.println(errorMsg);
        return errorMsg;
    }

    String result = "Option selected - " + optionName;
    System.out.println(result);
    return result;
  }
}
