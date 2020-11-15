package com.example.MockProject.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		
		// 1. Create pay load containing exception details
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;

		ApiException apiException = new ApiException(
			e.getMessage(),
			null,
			HttpStatus.BAD_REQUEST,
			ZonedDateTime.now(ZoneId.of("Z"))
		);

		// 2. Return actual response entity
		
		return new ResponseEntity<>(apiException, badRequest);
	}

}
//TODO : logger file + apiexception - 2 constructor(message, throwable)