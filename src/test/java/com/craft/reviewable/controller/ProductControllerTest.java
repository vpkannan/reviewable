package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.domain.error.ReviewableError;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.service.ProductService;

public class ProductControllerTest {

	@Test
	public void testGetAllProducts() throws Exception {
		ProductService productService = mock(ProductService.class);
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		when(productService.getAllProducts()).thenReturn(products);
		ProductController controller = new ProductController(productService);
		ResponseEntity<List<Product>> retrievedProducts = controller.getAllProducts();
		assertEquals(retrievedProducts.getBody().size(), 2);
		assertEquals(retrievedProducts.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testGetProductById() throws Exception {
		ProductService productService = mock(ProductService.class);
		Product product = new Product();
		product.setId("abc1234");
		product.setName("IPhone 8");
		product.setAverageRating(3.0);
		product.setFiveStar(1);
		product.setFourStar(1);
		product.setThreeStar(1);
		product.setTwoStar(1);
		product.setOneStar(1);

		when(productService.getProductById("abc1234")).thenReturn(product);
		ProductController controller = new ProductController(productService);
		ResponseEntity<Product> retrievedProduct = controller.getProductById("abc1234");
		assertEquals(retrievedProduct.getBody().getId(), "abc1234");
		assertEquals(retrievedProduct.getBody().getAverageRating(), product.getAverageRating(), 0.0);
		assertEquals(retrievedProduct.getBody().getOneStar(), product.getOneStar());
		assertEquals(retrievedProduct.getBody().getTwoStar(), product.getTwoStar());
		assertEquals(retrievedProduct.getBody().getThreeStar(), product.getThreeStar());
		assertEquals(retrievedProduct.getBody().getFourStar(), product.getFourStar());
		assertEquals(retrievedProduct.getBody().getFiveStar(), product.getFiveStar());
		assertEquals(retrievedProduct.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testCreateProduct() throws Exception {
		ProductService productService = mock(ProductService.class);
		Product mockProduct = new Product();
		mockProduct.setId("abc1234");
		mockProduct.setName("IPhone 8");
		Product product = new Product();
		product.setName("IPhone 8");
		when(productService.createProduct(product)).thenReturn(mockProduct);
		ProductController controller = new ProductController(productService);
		ResponseEntity<Product> createdProduct = controller.createProduct(product);
		assertEquals(createdProduct.getBody().getName(), product.getName());
		assertEquals(createdProduct.getStatusCode(), HttpStatus.CREATED);
	}

	@Test(expected = ReviewableException.class)
	public void testGetAllProductsErrorScenario() throws Exception {
		ProductService productService = mock(ProductService.class);
		when(productService.getAllProducts())
				.thenThrow(new ReviewableException(new ReviewableError("ERROR1", "ERROR_OCCURRED")));
		ProductController controller = new ProductController(productService);
		controller.getAllProducts();
	}

	@Test(expected = ReviewableException.class)
	public void testGetProductByIdErrorScenario() throws Exception {
		ProductService productService = mock(ProductService.class);
		when(productService.getProductById("prod1"))
				.thenThrow(new ReviewableException(new ReviewableError("ERROR1", "ERROR_OCCURRED")));
		ProductController controller = new ProductController(productService);
		controller.getProductById("prod1");

	}

}
