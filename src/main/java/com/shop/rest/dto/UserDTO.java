package com.shop.rest.dto;

import com.shop.rest.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
  private Long id;
  private String firstName;
  private String email;
  private String password;
  private Role userType;
}
