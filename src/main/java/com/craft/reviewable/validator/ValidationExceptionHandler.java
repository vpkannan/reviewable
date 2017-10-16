/**
 * 
 */
package com.craft.reviewable.validator;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.craft.reviewable.domain.error.ReviewableError;

/**
 * @author Vignesh
 *
 */
@ControllerAdvice
@EnableWebMvc
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.validator.ValidationExceptionHandler.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();

		List<ReviewableError> reviewableErrors = new ArrayList<>();

		for (ObjectError error : errors) {
			ReviewableError reviewableError = new ReviewableError(error.getCode(), error.getDefaultMessage());
			reviewableErrors.add(reviewableError);
		}

		return new ResponseEntity<>(reviewableErrors, status);
	}
}
