package com.craft.reviewable.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

public class ReviewsTest {
	@Test
	public void testProductEquals() throws Exception {

		Date date = new Date();
		Product p = new Product("prod1", "IPhone X", 3.0, 1, 1, 1, 1, 1);
		Review r1 = new Review("review1", "prod1", 5, "Good product", "Excellent design", "John Doe", date);
		Review r2 = new Review("review2", "prod1", 5, "Good product", "Excellent design", "John Doe", date);
		Review r3 = new Review("review1", "prod1", 5, "Good product", "Excellent design", "John Doe", date);

		assertEquals(r1, r3);
		assertEquals(r1.hashCode(), r3.hashCode());
		assertNotEquals(r1, r2);
		assertNotEquals(r1, p);
		assertEquals(r1, r1);
	}
}
