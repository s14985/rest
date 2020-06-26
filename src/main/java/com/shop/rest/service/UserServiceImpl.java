package com.shop.rest.service;

import com.shop.rest.config.mapper.UserMapper;
import com.shop.rest.dto.UserDTO;
import com.shop.rest.exception.ResourceNotFoundException;
import com.shop.rest.model.Order;
import com.shop.rest.model.User;
import com.shop.rest.repository.UserRepository;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  private String getCurrentUserLogin() {
    Object principal = SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getPrincipal();
    String username = "";
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    return username;
  }

  @Override
  public @NotNull Iterable<UserDTO> getAllUsers() {
    return userMapper.toDto(userRepository.findAll());
  }

  @Override
  public UserDTO getUserById(
    @Min(value = 1L, message = "Invalid user ID.") Long id
  ) {
    return userMapper.toDto(
      userRepository
        .findById(id)
        .orElseThrow(
          () -> new ResourceNotFoundException("user", "id", id.toString())
        )
    );
  }

  @Override
  public UserDTO save(UserDTO user) {
    return userMapper.toDto(userRepository.save(userMapper.toModel(user)));
  }

  @Override
  public UserDTO getCurrentUser() {
    String login = getCurrentUserLogin();
    return userMapper.toDto(
      userRepository
        .findByEmail(login)
        .orElseThrow(
          () -> new ResourceNotFoundException("user", "email", login)
        )
    );
  }
}
