package com.neosoft.task1.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exc) {
		ErrorResponse msg = new ErrorResponse(400, "please enter proper fields", new Date());
		return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<?> handleValidationException(UserNotFoundException exc) {
		ErrorResponse msg = new ErrorResponse(404, exc.getMessage(), new Date());
		return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);

	}

}
