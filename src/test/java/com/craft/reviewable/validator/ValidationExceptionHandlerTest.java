package com.craft.reviewable.validator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class ValidationExceptionHandlerTest {

	@Test
	public void testHandleReviewableExceptionHandler() {

		ValidationExceptionHandler handler = new ValidationExceptionHandler();
		List<ObjectError> errors = new ArrayList<>();
		errors.add(new ObjectError("review", new String[] { "ERROR1" }, null, "FATAL_ERROR"));
		BindingResult bindingResult = new BeanPropertyBindingResult(errors, "errors");
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);
		ResponseEntity<Object> error = handler.handleMethodArgumentNotValid(ex, null, HttpStatus.INTERNAL_SERVER_ERROR,
				null);

		assertEquals(error.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
