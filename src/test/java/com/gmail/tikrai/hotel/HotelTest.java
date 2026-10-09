package com.gmail.tikrai.hotel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HotelTest {

  @Test
  void testHotelInitiation() {
    Hotel hotel = Hotel.initiate(new HotelProperties(4, 10));

    assertEquals(4, hotel.getRooms().size());
    assertEquals(1, hotel.getRooms().get(0).getNumber());
    assertEquals(2, hotel.getRooms().get(1).getNumber());
    assertEquals(3, hotel.getRooms().get(2).getNumber());
    assertEquals(4, hotel.getRooms().get(3).getNumber());
    assertEquals(10, hotel.getRooms().get(0).getPrice());
    assertEquals(15, hotel.getRooms().get(1).getPrice());
    assertEquals(10, hotel.getRooms().get(2).getPrice());
    assertEquals(15, hotel.getRooms().get(3).getPrice());
  }

  @Test
  void testEmptyHotelRoomAvailability() {
    Hotel hotel = Hotel.initiate(new HotelProperties(2, 10));

    assertEquals("All rooms are empty", hotel.availability());
  }

  @Test
  void testCheckin() {
    Hotel hotel = Hotel.initiate(new HotelProperties(2, 10));
    Scanner scanner = Mockito.mock(Scanner.class);
    Random random = Mockito.mock(Random.class);
    Mockito.when(scanner.nextLine())
        .thenReturn("")
        .thenReturn("John")
        .thenReturn("")
        .thenReturn("Smith");
    Mockito.when(random.nextInt(2)).thenReturn(1);

    hotel.checkin(scanner, random);

    assertEquals("Occupied rooms:\n" + "2. John Smith", hotel.availability());
  }

  @Test
  void testEmptyHotelCheckin() {
    Hotel hotel = Hotel.initiate(new HotelProperties(0, 10));
    Scanner scanner = Mockito.mock(Scanner.class);
    Random random = Mockito.mock(Random.class);

    hotel.checkin(scanner, random);

    assertEquals("All rooms are empty", hotel.availability());
  }

  @Test
  void testCheckOut() {
    Hotel hotel = Hotel.initiate(new HotelProperties(2, 10));
    Scanner scanner = Mockito.mock(Scanner.class);
    Random random = Mockito.mock(Random.class);
    Mockito.when(scanner.nextLine()).thenReturn("John").thenReturn("Smith");
    Mockito.when(random.nextInt(2)).thenReturn(1);
    Mockito.when(scanner.hasNextInt()).thenReturn(true);
    Mockito.when(scanner.nextInt()).thenReturn(7).thenReturn(2);

    hotel.checkin(scanner, random);
    hotel.checkout(scanner);

    assertEquals("All rooms are empty", hotel.availability());
  }
}
