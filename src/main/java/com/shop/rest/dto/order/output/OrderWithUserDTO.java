package com.shop.rest.dto.order.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.rest.dto.user.output.UserDTO;
import com.shop.rest.model.Status;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderWithUserDTO {
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private OffsetDateTime dateCreated;

  private Status status;

  private UserDTO user;
}
