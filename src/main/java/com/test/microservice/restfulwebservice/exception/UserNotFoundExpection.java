package com.test.microservice.restfulwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundExpection extends RuntimeException {
	public UserNotFoundExpection(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
