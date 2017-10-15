package com.craft.reviewable.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductTest {
	@Test
	public void testCalculateAverageRating() throws Exception {
		Product product = new Product("prod1", "IPhone X", 3.0, 1, 1, 1, 1, 1);
		int newReviewRating = 4;
		double newAverageRating = product.calculateNewAverageRating(newReviewRating);
		product.setAverageRating(newAverageRating);
		assertEquals(newAverageRating, 3.2, 0.0);
		assertEquals(product.getAverageRating(), 3.2, 0.0);
		assertEquals(product.getOneStar(), 1);
		assertEquals(product.getTwoStar(), 1);
		assertEquals(product.getThreeStar(), 1);
		assertEquals(product.getFourStar(), 2);
		assertEquals(product.getFiveStar(), 1);
	}
}
