package com.avenuecode.api.service;

import java.util.List;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Product;

/**
 * This interface describes the operations supported by the application to
 * manage products.
 *
 * @author Luis Eduardo Oliveira Lizardo
 *
 */
public interface ProductService {

	/**
	 * Get all products.
	 * 
	 * @return list of products.
	 */
	public List<Product> getAllProducts();

	/**
	 * Get all products excluding relationships.
	 * 
	 * @return list of products excluding relationships.
	 */
	public List<Product> getAllProductsSimple();

	/**
	 * Get a product by its specified id.
	 * 
	 * @param id
	 *            of the product.
	 * @return a product.
	 */
	public Product getProductById(long id);

	/**
	 * Get a list of sub-products of the specified product.
	 * 
	 * @param id
	 *            of the product
	 * @return list of subproducts.
	 */
	public List<Product> getSubProducts(long id);

	/**
	 * Get a list of images of the specified product.
	 * 
	 * @param id
	 *            of the product
	 * @return list of images.
	 */
	public List<Image> getProductImages(long productId);

	/**
	 * Saves a product on the database.
	 * 
	 * @param product
	 *            to be saved.
	 * @return the product with is generated id.
	 */
	public Product saveProduct(Product product);

	/**
	 * Deletes a product on the database.
	 * 
	 * @param product
	 *            to be deleted.
	 */
	public void removeProduct(Product product);
}
