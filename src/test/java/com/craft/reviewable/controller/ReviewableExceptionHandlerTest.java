package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;

public class ReviewableExceptionHandlerTest {

	/**
	 * Test: Method to handle ReviewableException
	 */
	@Test
	public void testHandleReviewableExceptionHandler() {
		ReviewableExceptionHandler handler = new ReviewableExceptionHandler();
		ResponseEntity<ReviewableError> error = handler.handleReviewableBadRequestException(new ReviewableException(
				new ReviewableError("ERROR1", "FATAL_ERROR"), HttpStatus.INTERNAL_SERVER_ERROR));

		assertEquals(error.getBody().getErrorCode(), "ERROR1");
		assertEquals(error.getBody().getErrorDescription(), "FATAL_ERROR");
		assertEquals(error.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Test: Method to handle other Exception types
	 */
	@Test
	public void testHandleExceptionHandler() {
		ReviewableExceptionHandler handler = new ReviewableExceptionHandler();
		ResponseEntity<ReviewableError> error = handler.handleException(new Exception());

		assertEquals(error.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
