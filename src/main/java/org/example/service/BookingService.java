package org.example.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.entity.Booking;
import org.example.exceptions.InvalidBookingRequestException;
import org.example.model.BookingRequest;
import org.example.model.BookingResponse;
import org.example.model.BookingStatus;
import org.example.repository.BookingRepository;
import org.example.utils.ErrorMessages;
import org.example.utils.RestaurantConstants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class BookingService {

  private final BookingRepository repository;

  public BookingResponse save(BookingRequest bookingRequest) {
    log.info("Initiating booking process for request: {}", bookingRequest);

    Booking booking = BookingMapper.toEntity(bookingRequest);

    if (!RestaurantConstants.isWithinWorkingHours(booking.getTime())) {
      log.warn("Booking time {} is outside working hours.", booking.getTime());
      booking.setStatus(BookingStatus.CANCELLED);
      return BookingMapper.toResponse(booking);
    }

    int requiredTables = RestaurantConstants.getTablesNeeded(booking.getTableSize());

    if (requiredTables > RestaurantConstants.TOTAL_TABLES) {
      log.warn("Requested table size {} exceeds total available tables.", booking.getTableSize());
      booking.setStatus(BookingStatus.CANCELLED);
      return BookingMapper.toResponse(booking);
    }

    LocalTime bookingStart = booking.getTime();
    LocalTime bookingEnd = bookingStart.plusMinutes(booking.getDurationMinutes());
    booking.setEndTime(bookingEnd);
    booking.setEndDate(booking.getDate());
    booking.setCreatedAt(booking.getDate());
    booking.setUpdatedAt(LocalDateTime.now());

    List<Booking> overlappingBookings =
        repository.findOverlappingBookings(
            booking.getDate(), bookingStart, bookingEnd, BookingStatus.CONFIRMED);

    int tablesInUse =
        overlappingBookings.stream()
            .mapToInt(b -> RestaurantConstants.getTablesNeeded(b.getTableSize()))
            .sum();

    boolean enoughCapacity = (tablesInUse + requiredTables) <= RestaurantConstants.TOTAL_TABLES;

    booking.setStatus(enoughCapacity ? BookingStatus.CONFIRMED : BookingStatus.PENDING);

    try {
      Booking savedBooking = null;
      if (booking.getStatus() != BookingStatus.PENDING) savedBooking = repository.save(booking);
      log.info("Booking saved successfully with status: {}", booking.getStatus());

      return BookingMapper.toResponse(booking);
    } catch (Exception e) {
      log.error("Unexpected error occurred while saving booking: {}", e.getMessage(), e);
      throw new InvalidBookingRequestException(ErrorMessages.INTERNAL_SERVER_ERROR);
    }
  }

  public List<BookingResponse> getBookingsByDate(String date) {
    log.info("Fetching bookings for date: {}", date);
    LocalDate bookingDate = LocalDate.parse(date);
    List<BookingResponse> bookings =
        repository.findByDate(bookingDate).stream()
            .map(BookingMapper::toResponse)
            .collect(Collectors.toList());
    log.info("Retrieved {} bookings for date: {}", bookings.size(), date);
    return bookings;
  }
}
