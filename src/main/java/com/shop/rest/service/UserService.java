package com.shop.rest.service;

import com.shop.rest.dto.user.output.UserDTO;

public interface UserService {
  UserDTO getUserById(Long id);
}
