package com.shop.rest.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable();
    http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
    http
      .cors()
      .and()
      .csrf()
      .disable()
      .httpBasic()
      .and()
      .formLogin()
      .failureUrl("/login")
      .defaultSuccessUrl("/api/user/current")
      .loginProcessingUrl("/login")
      .usernameParameter("username")
      .passwordParameter("password")
      .permitAll()
      .and()
      .logout()
      .logoutUrl("/logout")
      .permitAll()
      .and()
      .authorizeRequests()
      .antMatchers("/**")
      .permitAll();
  }
}
