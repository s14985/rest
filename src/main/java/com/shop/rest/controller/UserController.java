package com.shop.rest.controller;

import com.shop.rest.model.User;
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
  public @NotNull Iterable<User> getUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public @NotNull User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/current")
  public @NotNull User getCurrentUser() {
    return userService.getCurrentUser();
  }

  @PutMapping("/current")
  public User updateCurrentUser(@RequestBody User user) {
    return userService.save(user);
  }
}
