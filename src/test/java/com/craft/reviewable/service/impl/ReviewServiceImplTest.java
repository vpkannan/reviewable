package com.craft.reviewable.service.impl;

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

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.domain.Review;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.repository.ProductRepository;
import com.craft.reviewable.repository.ReviewRepository;
import com.craft.reviewable.service.ReviewService;

public class ReviewServiceImplTest {
	@Test
	public void testListProductReviews() throws Exception {
		ReviewRepository reviewRepository = mock(ReviewRepository.class);
		ProductRepository productRepository = mock(ProductRepository.class);
		Review r1 = new Review("review1", "prod1", 5, "Good product", "Excellent design", "John Doe", new Date());
		Review r2 = new Review("review2", "prod1", 4, "I am happy customer", "Enjoying the product", "Jane Doe",
				new Date());

		List<Review> reviews = new ArrayList<>();
		reviews.add(r1);
		reviews.add(r2);
		Page<Review> page = new PageImpl<>(reviews, null, 2);
		when(reviewRepository.findByProductId(any(String.class), any(Pageable.class))).thenReturn(page);
		ReviewService reviewService = new ReviewServiceImpl(reviewRepository, productRepository);
		Page<Review> retrievedReviews = reviewService.listProductReviews("prod1", null);
		for (Review review : retrievedReviews.getContent()) {
			assertEquals(review.getProductId(), "prod1");
		}
	}

	@Test
	public void testAddReview() throws Exception {
		ReviewRepository reviewRepository = mock(ReviewRepository.class);
		ProductRepository productRepository = mock(ProductRepository.class);
		Review reviewRequest = new Review("", "prod1", 5, "Good product", "Excellent design", "John Doe", null);
		Review reviewResponse = new Review("review1", "prod1", 5, "Good product", "Excellent design", "John Doe",
				new Date());
		Product product = new Product("prod1", "IPhone X", 3.0, 1, 1, 1, 1, 1);

		when(productRepository.findOne(reviewRequest.getProductId())).thenReturn(product);
		when(productRepository.save(product)).thenReturn(product);
		when(reviewRepository.save(reviewRequest)).thenReturn(reviewResponse);

		ReviewService reviewService = new ReviewServiceImpl(reviewRepository, productRepository);

		Review createdReview = reviewService.addReview(reviewRequest);

		assertNotNull(createdReview.getId());
		assertNotNull(createdReview.getDate());

	}

	@Test(expected = ReviewableException.class)
	public void testListProductReviewsForInvalidProduct() throws Exception {
		ReviewRepository reviewRepository = mock(ReviewRepository.class);
		ProductRepository productRepository = mock(ProductRepository.class);

		List<Review> reviews = new ArrayList<>();
		Page<Review> page = new PageImpl<>(reviews, null, 0);

		when(reviewRepository.findByProductId(any(String.class), any(Pageable.class))).thenReturn(page);
		ReviewService reviewService = new ReviewServiceImpl(reviewRepository, productRepository);
		reviewService.listProductReviews("prod1", null);
	}

	@Test(expected = ReviewableException.class)
	public void testAddReviewForInvalidProject() throws Exception {
		ReviewRepository reviewRepository = mock(ReviewRepository.class);
		ProductRepository productRepository = mock(ProductRepository.class);
		Review review = new Review("", "prod1", 5, "Good product", "Excellent design", "John Doe", null);

		when(productRepository.findOne(any(String.class))).thenReturn(null);

		ReviewService reviewService = new ReviewServiceImpl(reviewRepository, productRepository);

		reviewService.addReview(review);

	}
}
