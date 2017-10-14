/**
 * 
 */
package com.craft.reviewable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam(value = "pid", required = true) String pid)
			throws ReviewableException {
		try {
			List<Review> productReviews = reviewService.listProductReviews(pid);
			return new ResponseEntity<List<Review>>(productReviews, HttpStatus.CREATED);
		} catch (ReviewableException ex) {
			throw ex;
		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addReview(@RequestBody Review review) throws ReviewableException {

		String addedReview = "";
		try {
			addedReview = reviewService.addReview(review);
		} catch (ReviewableException ex) {
			throw ex;
		}
		return new ResponseEntity<String>(addedReview, HttpStatus.CREATED);

	}

}
