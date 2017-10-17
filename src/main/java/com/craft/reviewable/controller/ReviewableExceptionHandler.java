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

/**
 * Exception handler for REST API
 */
@ControllerAdvice
@EnableWebMvc
@Order(Ordered.LOWEST_PRECEDENCE)
public class ReviewableExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger SYS_LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.controller.ReviewableExceptionHandler.class);

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
		SYS_LOGGER.info("ControllerAdvice returning error message to user");
		SYS_LOGGER.debug("Error returned: {}", ex.getError());
		return new ResponseEntity<>(ex.getError(), ex.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ReviewableError> handleException(final Exception ex) {
		SYS_LOGGER.info("ControllerAdvice returning error message to user");
		SYS_LOGGER.debug("Exception details: {}", ex);
		String cause = (ex.getMessage() == null) ? "Internal Server Error. Please contact admin for more details"
				: ex.getMessage();
		ReviewableError error = new ReviewableError("", cause);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}