package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.craft.reviewable.domain.Review;
import com.craft.reviewable.service.ReviewService;
import com.craft.reviewable.validator.ReviewValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewController.class, secure = false)
public class ReviewControllerHttpTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ReviewService reviewService;

	@MockBean
	ReviewValidator reviewValidator;

	@Test
	public void testAddReviewWithHttp() throws Exception {

		String requestString = "{\r\n" + "	\"productId\": \"prod1\",\r\n" + "	\"rating\": 4,\r\n"
				+ "	\"reviewTitle\": \"Good product\",\r\n" + "	\"reviewText\": \"Good and helpful Product\",\r\n"
				+ "	\"userName\": \"user1\"\r\n" + "}";

		when(reviewValidator.supports(any(Class.class))).thenReturn(true);

		when(reviewService.addReview(constructReviewRequest())).thenReturn(constructAddReviewResponse());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1.0/review").accept(MediaType.APPLICATION_JSON)
				.content(requestString).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getStatus(), HttpStatus.CREATED.value());

	}

	private Review constructReviewRequest() {
		return new Review(null, "prod1", 4, "Good product", "Good and helpful Product", "user1", null);
	}

	private Review constructAddReviewResponse() {
		return new Review("review1", "prod1", 4, "Good product", "Good and helpful Product", "user1", new Date());
	}
}
