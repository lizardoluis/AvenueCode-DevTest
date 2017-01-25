package com.avenuecode.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Product;
import com.avenuecode.api.entity.Response;
import com.avenuecode.api.service.ProductService;
import com.avenuecode.core.exception.ProductException;

@RestController
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/*
	 * Get all products
	 */
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam("simple") Optional<Boolean> simple)
			throws ProductException {

		List<Product> products = null;

		// Checks if the simple parameter was passed and its true.
		if (simple.isPresent() && simple.get() == true) {
			logger.info("Returning all Products excluding relationships");
			products = productService.getAllProductsSimple();

		} else {
			logger.info("Returning all Products");
			products = productService.getAllProducts();
		}

		if (products == null || products.size() == 0) {
			throw new ProductException("Products not found");
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/*
	 * Get specic product
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id,
			@RequestParam("simple") Optional<Boolean> simple) throws ProductException {

		logger.info("Returning product with Id " + id);
		Product product = productService.getProductById(id);

		if (product == null || product.getId() <= 0) {
			throw new ProductException("Product doesn´t exist");
		}

		// Checks if the simple parameter was passed and its true.
		if (simple.isPresent() && simple.get() == true) {
			product.setImages(null);
			product.setSubProducts(null);
		}

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/*
	 * Get images from product
	 */
	@RequestMapping(value = "/products/{id}/images", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getProductImages(@PathVariable("id") long id) {
		logger.info("Returning all the images from Products id " + id);
		List<Image> images = productService.getProductImages(id);
		return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
	}

	/*
	 * Get subproducts from product
	 */
	@RequestMapping(value = "/products/{id}/subproducts", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getSubProducts(@PathVariable("id") long id) {
		logger.info("Returning all the subproducts from Products id " + id);
		List<Product> products = productService.getSubProducts(id);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/*
	 * Delete
	 */
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeProductById(@PathVariable("id") long id) throws ProductException {
		logger.info("Product id to remove " + id);
		Product product = productService.getProductById(id);

		if (product == null) {
			throw new ProductException("Product to delete doesn´t exist");
		}

		productService.removeProduct(product);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Product has been deleted"),
				HttpStatus.OK);
	}

	/*
	 * Create
	 */
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Product> saveProduct(@RequestBody Product payload) throws ProductException {
		logger.info("Payload to save " + payload);
		if (payload.getId() > 0) {
			throw new ProductException("Payload malformed, id must not be defined");
		}
		return new ResponseEntity<Product>(productService.saveProduct(payload), HttpStatus.OK);
	}

	/*
	 * Update
	 */
	@RequestMapping(value = "/products", method = RequestMethod.PATCH)
	public ResponseEntity<Product> updateProduct(@RequestBody Product payload) throws ProductException {
		logger.info("Payload to update " + payload);
		Product product = productService.getProductById(payload.getId());
		if (product == null) {
			throw new ProductException("Product to update doesn´t exist");
		}
		return new ResponseEntity<Product>(productService.saveProduct(payload), HttpStatus.OK);
	}
}
