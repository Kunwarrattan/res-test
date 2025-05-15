package org.example.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
  private boolean success;
  private String message;
  private List<String> errors;
  private LocalDateTime timestamp;
  private int errorCode;
  private int status;
  private String path;
  private HashMap<String, String> metaData;
}
