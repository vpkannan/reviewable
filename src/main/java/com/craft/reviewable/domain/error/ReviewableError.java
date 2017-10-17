package com.craft.reviewable.domain.error;

/**
 * Customized error entity returned by Reviewable application in error scenarios
 * 
 * @author Vignesh
 *
 */
public class ReviewableError {

	private String errorCode;
	private String errorDescription;

	public ReviewableError() {
		super();
	}

	public ReviewableError(String errorCode, String errorDescription) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

}
