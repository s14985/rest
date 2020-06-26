package com.shop.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.rest.model.Status;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private OffsetDateTime dateCreated;

  private Status status;

  private UserDTO user;
}
