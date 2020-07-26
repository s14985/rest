package com.shop.rest.service;

import com.shop.rest.config.mapper.UserMapper;
import com.shop.rest.dto.user.UserDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.User;
import com.shop.rest.repository.UserRepository;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  private User getModel(
    @Min(value = 1L, message = "Invalid user ID.") Long id
  ) {
    return userRepository
      .findById(id)
      .orElseThrow(
        () -> new ResourceNotFoundException("user", "id", id.toString())
      );
  }

  @Override
  public UserDTO getUserById(Long id) {
    return userMapper.toUserDto(getModel(id));
  }
}
