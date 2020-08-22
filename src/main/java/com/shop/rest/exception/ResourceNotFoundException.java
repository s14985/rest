package com.shop.rest.exception;

import org.springframework.util.StringUtils;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(
		String resource,
		String paramName,
		String param
	) {
		super(
			StringUtils.capitalize(resource) +
			" with " +
			StringUtils.capitalize(paramName) +
			": " +
			param +
			" not found."
		);
	}
}
