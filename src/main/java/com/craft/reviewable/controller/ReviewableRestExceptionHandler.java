package com.craft.reviewable.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.craft.reviewable.domain.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;

@ControllerAdvice
@EnableWebMvc
@Order(Ordered.LOWEST_PRECEDENCE)
public class ReviewableRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ReviewableException.class)
	@ResponseBody
	public ResponseEntity<ReviewableError> handleReviewableBadRequestException(final ReviewableException ex) {
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	// @ExceptionHandler({ RepositoryConstraintViolationException.class })
	// public ResponseEntity<ReviewableError> handleAccessDeniedException(Exception
	// ex, WebRequest request) {
	// RepositoryConstraintViolationException nevEx =
	// (RepositoryConstraintViolationException) ex;
	//
	// String errors = nevEx.getErrors().getAllErrors().stream().map(p ->
	// p.toString())
	// .collect(Collectors.joining("\n"));
	//
	// return new ResponseEntity<ReviewableError>(errors, HttpStatus.BAD_REQUEST);
	// }
}