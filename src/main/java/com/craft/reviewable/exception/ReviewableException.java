package com.craft.reviewable.exception;

import org.springframework.http.HttpStatus;

import com.craft.reviewable.domain.error.ReviewableError;

/**
 * Customized Exception that holds ReviewableError to throw appropriate error
 * messages during error scenarios
 * 
 * @author Vignesh
 *
 */
public class ReviewableException extends Exception {

	private static final long serialVersionUID = 1L;
	private transient final ReviewableError error;
	private final HttpStatus httpStatus;

	public ReviewableException(ReviewableError error, HttpStatus httpStatus) {
		super();
		this.error = error;
		this.httpStatus = httpStatus;
	}

	public ReviewableError getError() {
		return error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
