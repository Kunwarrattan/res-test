package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonUtil {

  private static final ObjectMapper objectMapper = createDefaultMapper();

  private static ObjectMapper createDefaultMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return mapper;
  }

  public static ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
