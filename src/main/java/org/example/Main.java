package org.example;

import org.example.controller.BookingController;
import org.example.repository.BookingRepository;
import org.example.service.BookingService;
import org.example.utils.JacksonUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {
    ObjectMapper mapper = JacksonUtil.getObjectMapper();
    BookingRepository repository = new BookingRepository();
    BookingService service = new BookingService(repository);
    BookingController controller = new BookingController(service);
    JacksonJsonProvider jacksonProvider = new JacksonJsonProvider(mapper);
    MuServer server =
        MuServerBuilder.httpServer()
            .withHttpPort(8080)
            .addHandler(
                RestHandlerBuilder.restHandler(controller)
                    .addCustomReader(jacksonProvider)
                    .addCustomWriter(jacksonProvider))
            .start();

    log.info("Server started at " + server.uri());
  }
}
