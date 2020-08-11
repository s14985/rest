package com.shop.rest.service;

import com.shop.rest.dto.user.UserDTO;

public interface UserService {
  UserDTO getUserById(Long id);
}
