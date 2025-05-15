package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.example.utils.MultipleOf15Between15And120;
import org.example.utils.ValidBookingTime;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
  @NotBlank(message = "Customer name is required")
  @Size(max = 100, message = "Customer name must not exceed 100 characters")
  private String customerName;

  @Min(value = 1, message = "Table size must be at least 1")
  @Max(value = 40, message = "Table size must not exceed 40")
  private Integer tableSize;

  @NotNull(message = "Date is required")
  @FutureOrPresent(message = "Date must be today or in the future")
  private LocalDate date;

  @NotNull(message = "Booking time is required")
  @ValidBookingTime
  private LocalTime time;

  @MultipleOf15Between15And120 private Integer durationMinutes;
}
