package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;

public class ControllerExceptionHandlerTest {

	@Test
	public void testHandleReviewableExceptionHandler() {
		ControllerExceptionHandler handler = new ControllerExceptionHandler();
		ResponseEntity<ReviewableError> error = handler.handleReviewableBadRequestException(
				new ReviewableException(new ReviewableError("ERROR1", "FATAL_ERROR")));

		assertEquals(error.getBody().getErrorCode(), "ERROR1");
		assertEquals(error.getBody().getErrorDescription(), "FATAL_ERROR");
		assertEquals(error.getStatusCode(), HttpStatus.NOT_FOUND);

	}

}
