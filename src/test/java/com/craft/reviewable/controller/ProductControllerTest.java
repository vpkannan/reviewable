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
		List<Product> retrievedProducts = controller.getAllProducts();
		assertEquals(retrievedProducts.size(), 2);
	}
}
