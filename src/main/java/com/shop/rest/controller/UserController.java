package com.shop.rest.controller;

import com.shop.rest.dto.UserDTO;
import com.shop.rest.service.UserService;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @GetMapping({ "", "/" })
  public @NotNull Iterable<UserDTO> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public @NotNull UserDTO getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/current")
  public @NotNull UserDTO getCurrentUser() {
    return userService.getCurrentUser();
  }

  @PutMapping("/current")
  public UserDTO updateCurrentUser(@RequestBody UserDTO user) {
    return userService.save(user);
  }
}
