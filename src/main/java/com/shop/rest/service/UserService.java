package com.shop.rest.service;

import com.shop.rest.dto.user.UserDTO;
import com.shop.rest.dto.user.UserWithAddressDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
  UserWithAddressDTO getUserWithAddressById(Long id);

  UserDTO getUserById(Long id);
}
