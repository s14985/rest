package com.shop.rest.service;

import com.shop.rest.model.User;
import com.shop.rest.repository.UserRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) {
    String email = login.toLowerCase(Locale.ENGLISH);
    return userRepository
      .findByEmail(email)
      .map(this::createSpringSecurityUser)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            "User with email" + email + " was not found in the database"
          )
      );
  }

  private org.springframework.security.core.userdetails.User createSpringSecurityUser(
    User user
  ) {
    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPassword(),
      user.getAuthorities()
    );
  }
}
