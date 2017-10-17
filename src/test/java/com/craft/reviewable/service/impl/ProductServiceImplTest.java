package com.craft.reviewable.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.exception.ReviewableException;
import com.craft.reviewable.repository.ProductRepository;
import com.craft.reviewable.service.ProductService;

public class ProductServiceImplTest {

	/**
	 * Test: Service method to get all Products
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllProducts() throws Exception {
		ProductRepository productRepository = mock(ProductRepository.class);
		List<Product> products = new ArrayList<>();
		products.add(new Product());
		products.add(new Product());
		when(productRepository.findAll()).thenReturn(products);
		ProductService productService = new ProductServiceImpl(productRepository);
		List<Product> retrievedProducts = productService.getAllProducts();
		assertEquals(retrievedProducts.size(), 2);
	}

	/**
	 * Test: Service method to get Product by ID
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductById() throws Exception {
		ProductRepository productRepository = mock(ProductRepository.class);
		Product product = new Product();
		product.setId("abc1234");
		product.setName("IPhone 8");
		when(productRepository.findOne("abc1234")).thenReturn(product);
		ProductService productService = new ProductServiceImpl(productRepository);
		Product retrievedProduct = productService.getProductById("abc1234");
		assertEquals(retrievedProduct.getId(), "abc1234");
		assertEquals(retrievedProduct.getName(), "IPhone 8");
	}

	/**
	 * Test: Service method to create Product
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateProduct() throws Exception {
		ProductRepository productRepository = mock(ProductRepository.class);
		Product mockProduct = new Product();
		mockProduct.setId("abc1234");
		mockProduct.setName("IPhone 8");
		Product product = new Product();
		product.setName("IPhone 8");
		when(productRepository.save(product)).thenReturn(mockProduct);
		ProductService productService = new ProductServiceImpl(productRepository);
		Product createdProduct = productService.createProduct(product);
		assertNotNull(createdProduct.getId());
		assertEquals(createdProduct.getName(), product.getName());
	}

	/**
	 * Test: Service method to get all Products - Error scenario
	 * 
	 * @throws Exception
	 */
	@Test(expected = ReviewableException.class)
	public void testGetAllProductsErrorScenario() throws Exception {
		ProductRepository productRepository = mock(ProductRepository.class);
		List<Product> products = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(products);
		ProductService productService = new ProductServiceImpl(productRepository);
		productService.getAllProducts();
	}

	/**
	 * Test: Service method to get Product by ID - Error scenario
	 * 
	 * @throws Exception
	 */
	@Test(expected = ReviewableException.class)
	public void testGetProductByIdErrorScenario() throws Exception {
		ProductRepository productRepository = mock(ProductRepository.class);
		when(productRepository.findOne(any(String.class))).thenReturn(null);
		ProductService productService = new ProductServiceImpl(productRepository);
		productService.getProductById("prod1");
	}

}
