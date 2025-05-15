package org.example.exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception exception) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity("An unexpected error occurred: " + exception.getMessage())
        .status(Response.Status.INTERNAL_SERVER_ERROR)
        .type(MediaType.TEXT_PLAIN)
        .build();
  }
}
