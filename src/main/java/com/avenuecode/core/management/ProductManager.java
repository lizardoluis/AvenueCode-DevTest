package com.avenuecode.core.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Product;
import com.avenuecode.api.service.ProductService;
import com.avenuecode.core.persistence.ProductRepository;

@Service("productService")
public class ProductManager implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void removeProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> getAllProductsSimple() {
		return productRepository.findAllSimple();
	}

	@Override
	public List<Product> getSubProducts(long id) {
		return productRepository.findSubProductsByProductId(id);
	}

	@Override
	public List<Image> getProductImages(long productId) {
		return productRepository.findImagesByProductId(productId);
	}
}
