package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.service.ReviewService;

public class ReviewControllerTest {

	@Test
	public void testGetReviewsForProducts() throws Exception {
		ReviewService reviewService = mock(ReviewService.class);
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review());
		reviews.add(new Review());
		Page<Review> page = new PageImpl<>(reviews, null, 2);
		when(reviewService.listProductReviews(any(String.class), any(Pageable.class))).thenReturn(page);
		ReviewController controller = new ReviewController(reviewService);
		ResponseEntity<Page<Review>> retrievedReviews = controller.getReviewsForProduct("abc123", null);
		assertEquals(retrievedReviews.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testAdddReview() throws Exception {
		ReviewService reviewService = mock(ReviewService.class);

		Review mockedReview = new Review();
		mockedReview.setId("abc1234");
		mockedReview.setProductId("xyz4321");
		mockedReview.setRating(5);
		mockedReview.setReviewTitle("Good Phone");
		mockedReview.setReviewText("Good Phone. Nice Camera");
		mockedReview.setUserName("John Doe");
		mockedReview.setDate(new Date());

		Review review = new Review();
		review.setProductId("xyz4321");
		review.setRating(5);
		review.setReviewTitle("Good Phone");
		review.setReviewText("Good Phone. Nice Camera");
		review.setUserName("John Doe");

		when(reviewService.addReview(review)).thenReturn(mockedReview);

		ReviewController controller = new ReviewController(reviewService);
		ResponseEntity<Review> addedReview = controller.addReview(review);
		assertEquals(addedReview.getBody().getId(), "abc1234");
		assertEquals(addedReview.getBody().getProductId(), review.getProductId());
		assertEquals(addedReview.getBody().getReviewTitle(), review.getReviewTitle());
		assertEquals(addedReview.getBody().getReviewText(), review.getReviewText());
		assertEquals(addedReview.getBody().getUserName(), review.getUserName());
		assertEquals(addedReview.getBody().getRating(), review.getRating());
		assertNotNull(addedReview.getBody().getDate());
		assertEquals(addedReview.getStatusCode(), HttpStatus.CREATED);
	}

	@Test(expected = ReviewableException.class)
	public void testGetReviewsForProductsErrorScenario() throws Exception {
		ReviewService reviewService = mock(ReviewService.class);
		when(reviewService.listProductReviews(any(String.class), any(Pageable.class)))
				.thenThrow(new ReviewableException(new ReviewableError("ERROR1", "ERROR_OCCURRED"),
						HttpStatus.INTERNAL_SERVER_ERROR));
		ReviewController controller = new ReviewController(reviewService);
		controller.getReviewsForProduct("abc123", null);
	}

	@Test(expected = ReviewableException.class)
	public void testAddReviewsErrorScenario() throws Exception {
		ReviewService reviewService = mock(ReviewService.class);
		when(reviewService.addReview(any(Review.class))).thenThrow(new ReviewableException(
				new ReviewableError("ERROR1", "ERROR_OCCURRED"), HttpStatus.INTERNAL_SERVER_ERROR));
		ReviewController controller = new ReviewController(reviewService);
		controller.addReview(new Review());
	}

}
