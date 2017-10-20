package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.service.ProductService;
import com.craft.reviewable.validator.ProductValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerHttpTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@MockBean
	ProductValidator productValidator;

	@Test
	public void testCreateProductWithHttp() throws Exception {

		String requestString = "{\r\n" + "	\"name\": \"Iphone X\"\r\n" + "}\r\n" + "";

		when(productValidator.supports(any(Class.class))).thenReturn(true);

		when(productService.createProduct(constructProductRequest())).thenReturn(constructAddProductResponse());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1.0/product").accept(MediaType.APPLICATION_JSON)
				.content(requestString).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void testGetProductDetailsWithHttp() throws Exception {

		when(productValidator.supports(any(Class.class))).thenReturn(true);

		when(productService.getProductById(any(String.class))).thenReturn(constructGetProductDetailsResponse());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1.0/product/prod1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	@Test
	public void testGetAllProductsWithHttp() throws Exception {

		when(productValidator.supports(any(Class.class))).thenReturn(true);

		when(productService.getAllProducts()).thenReturn(constructGetAllProductsResponse());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1.0/product/all")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(response.getStatus(), HttpStatus.OK.value());

	}

	private List<Product> constructGetAllProductsResponse() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("prod1", "Iphone X", 3.0, 1, 1, 1, 1, 1));
		products.add(new Product("prod2", "Iphone 8", 3.0, 1, 1, 1, 1, 1));
		products.add(new Product("prod3", "Iphone 8 plus", 3.0, 1, 1, 1, 1, 1));
		return products;
	}

	private Product constructGetProductDetailsResponse() {
		return new Product("prod1", "Iphone X", 3.0, 1, 1, 1, 1, 1);
	}

	private Product constructProductRequest() {
		return new Product(null, "prod1", 0.0, 0, 0, 0, 0, 0);
	}

	private Product constructAddProductResponse() {
		return new Product("prod1", "Iphone X", 0.0, 0, 0, 0, 0, 0);
	}
}
