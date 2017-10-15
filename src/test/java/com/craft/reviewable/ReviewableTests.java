package com.craft.reviewable;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;

import com.craft.reviewable.domain.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ReviewableTests {

	@Test
	public void testGreeting() throws Exception {
		int n = 5;
		Product p = new Product();
		p.setName("Iphone 8");
		assertEquals(5, n);
	}

}
