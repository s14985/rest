package com.shop.rest.exception;


import org.springframework.util.StringUtils;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(
    String resourse,
    String paramName,
    String param
  ) {
    super(
      StringUtils.capitalize(resourse) +
      " with " +
      StringUtils.capitalize(paramName) +
      ": " +
      param +
      " not found."
    );
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
