package org.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.example.model.ApiResponse;
import org.example.model.BookingRequest;
import org.example.model.BookingResponse;
import org.example.service.BookingService;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/rbooking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createBooking(@Valid BookingRequest booking) {
    BookingResponse response = bookingService.save(booking);

    ApiResponse<BookingResponse> apiResponse =
        ApiResponse.<BookingResponse>builder().data(response).notification(null).build();

    return Response.status(Response.Status.CREATED).entity(apiResponse).build();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBookings(@QueryParam("date") String date) {
    LocalDate iDate = LocalDate.parse(date);
    List<BookingResponse> bookings = bookingService.getBookingsByDate(date);

    ApiResponse<List<BookingResponse>> apiResponse =
        ApiResponse.<List<BookingResponse>>builder().data(bookings).notification(null).build();
    return Response.ok(apiResponse).build();
  }
}
