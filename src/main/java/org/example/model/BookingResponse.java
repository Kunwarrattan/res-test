package org.example.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
  private Long id;
  private String customerName;
  private int tableSize;
  private String date;
  private String time;
  private int durationMinutes;
  private String bookingDate;
  private String bookingTime;
  private String status;
}
