package org.example.utils;

import java.time.LocalTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidBookingTimeValidator implements ConstraintValidator<ValidBookingTime, LocalTime> {
  @Override
  public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    if (value.isBefore(RestaurantConstants.OPENING_TIME)
        || value.isAfter(RestaurantConstants.CLOSING_TIME)) {
      return false;
    }

    // 15-minute interval check
    int minute = value.getMinute();
    return minute % 15 == 0;
  }
}
