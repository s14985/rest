package com.shop.rest.service;

import com.shop.rest.dto.UserDTO;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
  @NotNull
  Iterable<UserDTO> getAllUsers();

  UserDTO getUserById(@Min(value = 1L, message = "Invalid user ID.") Long id);

  UserDTO save(UserDTO user);

  UserDTO getCurrentUser();
}
