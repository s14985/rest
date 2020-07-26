package com.shop.rest.service;

import com.shop.rest.dto.user.UserDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
  UserDTO getUserById(Long id);
}
