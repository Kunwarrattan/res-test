package org.example.utils;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = MultipleOf15Between15And120Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleOf15Between15And120 {
  String message() default "Duration must be a multiple of 15 between 15 and 120 minutes";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
