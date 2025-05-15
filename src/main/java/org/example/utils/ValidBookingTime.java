package org.example.utils;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = ValidBookingTimeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookingTime {
  String message() default "Booking time must be within operating hours and at 15-minute intervals";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
