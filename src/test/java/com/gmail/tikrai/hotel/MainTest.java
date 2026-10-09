package com.gmail.tikrai.hotel;

import org.junit.jupiter.api.Test;

import static com.gmail.tikrai.hotel.Main.actions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

  @Test
  public void testValidOptions() {
    assertEquals(actions.get(0), Main.handleChoice(actions, 1));
    assertEquals(actions.get(1), Main.handleChoice(actions, 2));
    assertEquals(actions.get(2), Main.handleChoice(actions, 3));
    assertEquals(actions.get(3), Main.handleChoice(actions, 4));
    assertEquals(actions.get(4), Main.handleChoice(actions, 5));
  }

  @Test
  public void testInvalidOption() {
    assertEquals(99, Main.handleChoice(actions, 99).id());
    assertEquals("Invalid", Main.handleChoice(actions, 99).name());
  }
}
