/**
 * 
 */
package com.craft.reviewable.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.service.ReviewService;

/**
 * @author Vignesh
 *
 */
@RestController
@RequestMapping("/v1.0/review")
public class ReviewController {

	public static final Logger LOGGER = LoggerFactory.getLogger(com.craft.reviewable.controller.ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	/**
	 * REST API to get all reviews for a given product based on the Product ID
	 * 
	 * @param pid
	 *            The product ID to look up its reviews
	 * @param pageable
	 *            The pagination attributes
	 * @return The Page of Review objects filtered based on Pageable
	 * @throws ReviewableException
	 */
	@RequestMapping(path = "/{pid}", method = RequestMethod.GET)
	public ResponseEntity<Page<Review>> getReviewsForProduct(@PathVariable final String pid, Pageable pageable)
			throws ReviewableException {
		LOGGER.info("Executing GET API on /v1.0/review/{pid}");
		LOGGER.debug("Requested product ID: {}", pid);
		try {
			Page<Review> productReviews = reviewService.listProductReviews(pid, pageable);
			return new ResponseEntity<>(productReviews, HttpStatus.OK);
		} catch (ReviewableException ex) {
			LOGGER.info("Throwing the exception customized error");
			throw ex;
		}

	}

	/**
	 * REST API to add review to a product
	 * 
	 * @param review
	 *            The Review object to be added
	 * @return The created Review object along with the auto generated ID
	 * @throws ReviewableException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Review> addReview(@RequestBody Review review) throws ReviewableException {
		LOGGER.info("Executing POST API on /v1.0/review");
		LOGGER.debug("New review details received by REST API: {}", review);
		try {
			Review addedReview = reviewService.addReview(review);
			return new ResponseEntity<>(addedReview, HttpStatus.CREATED);
		} catch (ReviewableException ex) {
			LOGGER.info("Throwing the exception customized error");
			throw ex;
		}

	}

}
