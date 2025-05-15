package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingDuration {
  MINUTES_15(15),
  MINUTES_30(30),
  MINUTES_45(45),
  MINUTES_60(60),
  MINUTES_75(75),
  MINUTES_90(90),
  MINUTES_105(105),
  MINUTES_120(120);

  private final int minutes;

  public static BookingDuration fromMinutes(int minutes) {
    for (BookingDuration duration : values()) {
      if (duration.getMinutes() == minutes) {
        return duration;
      }
    }
    throw new IllegalArgumentException("Invalid duration: " + minutes);
  }
}
