package com.gmail.tikrai.hotel;

import org.junit.jupiter.api.Test;

import static com.gmail.tikrai.hotel.Main.actions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

  @Test
  public void testValidOptions() {
    assertEquals(actions.get(0), Main.handleChoice(1));
    assertEquals(actions.get(1), Main.handleChoice(2));
    assertEquals(actions.get(2), Main.handleChoice(3));
    assertEquals(actions.get(3), Main.handleChoice(4));
    assertEquals(actions.get(4), Main.handleChoice(5));
  }

  @Test
  public void testInvalidOption() {
    assertEquals(99, Main.handleChoice(99).id());
    assertEquals("Invalid", Main.handleChoice(99).name());
  }
}
