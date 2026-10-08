package com.gmail.tikrai.hotel;

import org.junit.jupiter.api.Test;

import static com.gmail.tikrai.hotel.Main.actions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

  @Test
  public void testValidOptions() {
    assertEquals("Option selected - Guest registration", Main.handleChoice(actions, 1));
    assertEquals("Option selected - Check-out of the guest", Main.handleChoice(actions, 2));
    assertEquals("Option selected - Review of room availability", Main.handleChoice(actions, 3));
    assertEquals("Option selected - Room occupancy history and status", Main.handleChoice(actions, 4));
    assertEquals("Option selected - Room occupancy report", Main.handleChoice(actions, 5));
  }

  @Test
  public void testInvalidOption() {
    assertEquals(
        "Option selected - Invalid choice. Please enter a number between 0 and 5",
        Main.handleChoice(actions, 99));
  }
}
