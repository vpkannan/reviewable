package com.craft.reviewable.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

	@Test
	public void testProductEquals() throws Exception {
		Product p1 = new Product("prod1", "IPhone X", 3.0, 1, 1, 1, 1, 1);
		Product p2 = new Product("prod2", "IPhone X", 3.0, 1, 1, 1, 1, 1);
		Product p3 = new Product("prod1", "IPhone X", 3.0, 1, 1, 1, 1, 1);
		Review r = new Review();

		assertEquals(p1, p3);
		assertEquals(p1.hashCode(), p3.hashCode());
		assertNotEquals(p1, p2);
		assertNotEquals(p1, r);
		assertEquals(p1, p1);
	}
}
