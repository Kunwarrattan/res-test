package org.example.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOf15Between15And120Validator
    implements ConstraintValidator<MultipleOf15Between15And120, Integer> {
  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return value >= 15 && value <= 120 && value % 15 == 0;
  }
}
