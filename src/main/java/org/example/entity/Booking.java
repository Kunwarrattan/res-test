package org.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.model.BookingStatus;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
  private Long id;
  private String customerName;
  private int tableSize;
  private LocalDate date;
  private LocalTime time;
  private int durationMinutes;
  private BookingStatus status;
  private LocalDate createdAt;
  private LocalDateTime updatedAt;
  private Long version;
  private LocalDate endDate;
  private LocalTime endTime;
}
