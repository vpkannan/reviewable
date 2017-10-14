/**
 * 
 */
package com.craft.reviewable.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.craft.reviewable.domain.Product;
import com.craft.reviewable.service.ProductService;
import com.craft.reviewable.validator.ProductValidator;

/**
 * @author Vignesh
 *
 */

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	ProductValidator productValidator;

	public static final Logger LOGGER = LoggerFactory
			.getLogger(com.craft.reviewable.controller.ProductController.class);

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Product getProductById(@RequestParam(value = "id", required = true) String id) {
		return productService.getProductById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		LOGGER.info("Incoming Product payload: {}", product);
		Product createdProduct = productService.createProduct(product);
		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}
}
