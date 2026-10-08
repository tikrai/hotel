package com.gmail.tikrai.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testValidOptions() {
        assertEquals("Option selected - Guest registration", Main.handleChoice(1));
        assertEquals("Option selected - Check-out of the guest", Main.handleChoice(2));
        assertEquals("Option selected - Review of room availability", Main.handleChoice(3));
        assertEquals("Option selected - Room occupancy history and status", Main.handleChoice(4));
        assertEquals("Option selected - Room occupancy report", Main.handleChoice(5));
    }

    @Test
    public void testInvalidOption() {
        assertEquals("Invalid choice. Please enter a number between 0 and 5.", Main.handleChoice(99));
    }
}
