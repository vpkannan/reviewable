package com.craft.reviewable.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;

@ControllerAdvice
@EnableWebMvc
@Order(Ordered.LOWEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.controller.ControllerExceptionHandler.class);

	/**
	 * Exception handler for the ReviewableException thrown by REST API
	 * 
	 * @param ex
	 *            The ReviewableException object constructed based on the error
	 * @return The human readable error object returned back to the user
	 */
	@ExceptionHandler(ReviewableException.class)
	@ResponseBody
	public ResponseEntity<ReviewableError> handleReviewableBadRequestException(final ReviewableException ex) {
		LOGGER.info("ControllerAdvice returning error message to user");
		LOGGER.debug("Error returned: {}", ex.getError());
		return new ResponseEntity<>(ex.getError(), HttpStatus.NOT_FOUND);
	}

}