/**
 * 
 */
package com.craft.reviewable.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Vignesh
 *
 */
@ControllerAdvice
@EnableWebMvc
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.validator.ValidationExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();

		return new ResponseEntity<>(errors, status);
	}
}
