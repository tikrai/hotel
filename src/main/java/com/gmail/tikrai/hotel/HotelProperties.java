package com.gmail.tikrai.hotel;

import java.io.InputStream;
import java.util.Properties;

public record HotelProperties(
    int roomCount,
    double basePrice
) {

  public static HotelProperties load() {
    Properties properties = new Properties();
    String fileName = "application.properties";
    try (InputStream is = Main.class.getClassLoader().getResourceAsStream(fileName)) {
      if (is == null) {
        return defaultProperties("config.properties not found");
      }

      properties.load(is);
      int roomCount = Integer.parseInt(properties.getProperty("hotel.roomCount", "6"));
      double basePrice = Double.parseDouble(properties.getProperty("hotel.basePrice", "20.0"));

      if (roomCount % 2 != 0) {
        return defaultProperties("room count in config.properties is odd");
      }

      return new HotelProperties(roomCount, basePrice);
    } catch (Exception e) {
      return defaultProperties("loading configuration failed with error: " + e.getMessage());
    }
  }

  private static HotelProperties defaultProperties(String error) {
    System.out.println("Warning: " + error + "\nUsing default properties\n");
    return new HotelProperties(6, 20.0);
  }
}
