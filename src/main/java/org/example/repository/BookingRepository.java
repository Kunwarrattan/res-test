package org.example.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.example.entity.Booking;
import org.example.model.BookingStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookingRepository {
  private final ConcurrentHashMap<Long, Booking> bookings = new ConcurrentHashMap<>();
  private final AtomicLong idGenerator = new AtomicLong();

  public Booking save(Booking booking) {
    Long id = idGenerator.incrementAndGet();
    booking.setId(id);
    bookings.put(id, booking);
    return booking;
  }

  public List<Booking> findByDate(LocalDate date) {
    List<Booking> result = new ArrayList<>();
    for (Booking booking : bookings.values()) {
      if (booking.getDate().equals(date)) {
        result.add(booking);
      }
    }
    return result;
  }

  public List<Booking> findOverlappingBookings(
      LocalDate date, LocalTime startTime, LocalTime endTime, BookingStatus status) {
    List<Booking> overlapping = new ArrayList<>();
    for (Booking b : bookings.values()) {
      if (b.getDate().equals(date) && b.getStatus() == status) {
        LocalTime bStart = b.getTime();
        LocalTime bEnd = b.getEndTime();
        if (bStart.isBefore(endTime) && bEnd.isAfter(startTime)) {
          overlapping.add(b);
        }
      }
    }
    return overlapping;
  }
}
