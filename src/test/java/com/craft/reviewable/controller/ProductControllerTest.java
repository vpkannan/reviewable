package com.craft.reviewable.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
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
		when(productService.getProductById("abc1234")).thenReturn(product);
		ProductController controller = new ProductController(productService);
		ResponseEntity<Product> retrievedProduct = controller.getProductById("abc1234");
		assertEquals(retrievedProduct.getBody().getId(), "abc1234");
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

}
