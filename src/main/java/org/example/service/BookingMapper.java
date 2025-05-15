package org.example.service;

import java.time.format.DateTimeFormatter;

import org.example.entity.Booking;
import org.example.model.BookingDuration;
import org.example.model.BookingRequest;
import org.example.model.BookingResponse;
import org.example.model.BookingStatus;

public class BookingMapper {
  private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

  public static Booking toEntity(BookingRequest request) {
    Booking booking = new Booking();
    booking.setCustomerName(request.getCustomerName());
    booking.setTableSize(request.getTableSize());
    booking.setDate(request.getDate());
    booking.setTime(request.getTime());
    booking.setDurationMinutes(
        request.getDurationMinutes() != null
            ? request.getDurationMinutes()
            : BookingDuration.MINUTES_120.getMinutes());
    booking.setStatus(BookingStatus.PENDING); // Default status, will be updated in service
    return booking;
  }

  public static BookingResponse toResponse(Booking booking) {
    BookingResponse response = new BookingResponse();
    response.setId(booking.getId());
    response.setCustomerName(booking.getCustomerName());
    response.setTableSize(booking.getTableSize());
    response.setDate(booking.getDate() != null ? booking.getDate().format(dateFormat) : null);
    response.setTime(booking.getTime() != null ? booking.getTime().format(timeFormat) : null);
    response.setDurationMinutes(booking.getDurationMinutes());
    response.setBookingDate(
        booking.getCreatedAt() != null ? booking.getCreatedAt().format(dateFormat) : null);
    response.setBookingTime(
        booking.getUpdatedAt() != null ? booking.getUpdatedAt().format(timeFormat) : null);
    response.setStatus(booking.getStatus() != null ? booking.getStatus().name() : "UNKNOWN");
    return response;
  }
}
