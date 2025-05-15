package org.example.utils;

import java.time.LocalTime;

public class RestaurantConstants {
  public static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
  public static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);
  public static final int TOTAL_TABLES = 10;
  public static final int SEATS_PER_TABLE = 4;

  public static boolean isWithinWorkingHours(LocalTime time) {
    return !time.isBefore(OPENING_TIME) && !time.isAfter(CLOSING_TIME.minusHours(2));
  }

  public static int getTablesNeeded(int partySize) {
    return (int) Math.ceil((double) partySize / SEATS_PER_TABLE);
  }

  public static int getMaxSeats() {
    return TOTAL_TABLES * SEATS_PER_TABLE;
  }
}
