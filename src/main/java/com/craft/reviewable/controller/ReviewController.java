/**
 * 
 */
package com.craft.reviewable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.service.ReviewService;

/**
 * @author Vignesh
 *
 */
@RestController
@RequestMapping("reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Review>> getAllReviews(@RequestParam(value = "pid", required = true) String pid,
			Pageable pageable) throws ReviewableException {
		try {
			Page<Review> productReviews = reviewService.listProductReviews(pid, pageable);
			return new ResponseEntity<Page<Review>>(productReviews, HttpStatus.CREATED);
		} catch (ReviewableException ex) {
			throw ex;
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Review> addReview(@RequestBody Review review) throws ReviewableException {

		try {
			Review addedReview = reviewService.addReview(review);
			return new ResponseEntity<Review>(addedReview, HttpStatus.CREATED);
		} catch (ReviewableException ex) {
			throw ex;
		}

	}

}
