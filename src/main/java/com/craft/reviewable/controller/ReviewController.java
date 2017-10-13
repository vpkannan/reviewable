/**
 * 
 */
package com.craft.reviewable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.craft.reviewable.domain.Review;
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
	public ResponseEntity<List<Review>> getAllReviews() {
		// TODO: call service
		return null;
	}

}
