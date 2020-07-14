package com.shop.rest.dto.user;

import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
}
