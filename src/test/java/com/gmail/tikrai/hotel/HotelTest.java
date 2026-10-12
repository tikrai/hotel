package com.gmail.tikrai.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HotelTest {

  @Mock private Scanner scanner;
  @Mock private Random random;
  private Hotel hotel;

  @BeforeEach
  void setup() {
    hotel = Hotel.initiate(new HotelProperties(4, 10), scanner, random);
  }

  @Test
  void testHotelInitiation() {
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
    assertEquals("All rooms are empty", hotel.availability());
  }

  @Test
  void testCheckin() {
    Mockito.when(scanner.nextLine())
        .thenReturn("")
        .thenReturn("John")
        .thenReturn("")
        .thenReturn("Smith");
    Mockito.when(random.nextInt(4)).thenReturn(1);

    assertEquals("Successful checkin in room 2. John Smith", hotel.checkin());
    assertEquals("Occupied rooms:\n" + "2. John Smith", hotel.availability());
  }

  @Test
  void testZeroRoomHotelCheckin() {
    hotel = Hotel.initiate(new HotelProperties(0, 10), scanner, random);
    assertEquals("No rooms are available", hotel.checkin());
    assertEquals("All rooms are empty", hotel.availability());
  }

  @Test
  void testCheckOut() {
    Mockito.when(scanner.nextLine()).thenReturn("John").thenReturn("Smith");
    Mockito.when(random.nextInt(4)).thenReturn(1);
    Mockito.when(scanner.hasNextInt()).thenReturn(true);
    Mockito.when(scanner.nextInt()).thenReturn(7).thenReturn(2);

    hotel.checkin();

    assertEquals("John Smith was successfully checked out", hotel.checkout());
    assertEquals("All rooms are empty", hotel.availability());
  }

  @Test
  void testEmptyHotelCheckOut() {
    Mockito.when(scanner.hasNextInt()).thenReturn(true);
    Mockito.when(scanner.nextInt()).thenReturn(2);

    assertEquals("Room is not occupied", hotel.checkout());
  }

  @Test
  void testRoomHistory() {
    Mockito.when(scanner.nextLine()).thenReturn("John").thenReturn("Smith");
    Mockito.when(random.nextInt(4)).thenReturn(1);
    Mockito.when(scanner.hasNextInt()).thenReturn(false).thenReturn(true);
    Mockito.when(scanner.nextInt()).thenReturn(0).thenReturn(1).thenReturn(2);

    hotel.checkin();

    assertEquals(
        """
            Guest history of room #1:

            ======
            Room is available""",
        hotel.roomHistory());
    assertEquals(
        """
            Guest history of room #2:
            John Smith
            ======
            Room is occupied""",
        hotel.roomHistory());
  }

  @Test
  void testProfitStat() {
    Mockito.when(scanner.nextLine()).thenReturn("John").thenReturn("Smith");
    Mockito.when(random.nextInt(4)).thenReturn(1);

    hotel.checkin();

    assertEquals(
        """
            #2 occupiedCount = 1, profit = 15.0
            #1 occupiedCount = 0, profit = 0.0
            #3 occupiedCount = 0, profit = 0.0
            #4 occupiedCount = 0, profit = 0.0""",
        hotel.occupancyReport());
  }
}
