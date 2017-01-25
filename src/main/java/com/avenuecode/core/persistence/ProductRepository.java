package com.avenuecode.core.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findById(@Param("productId") long productId);
	
	@Query("SELECT p.id, p.name, p.description FROM Product p WHERE p.id = :productId")
    public List<Product> findSimpleProductById(@Param("productId") long productId);
	
	@Query("SELECT p.id, p.name, p.description FROM Product p")
	public List<Product> findAllSimple();
	
	@Query("SELECT p.images FROM Product p WHERE p.id = :productId")
	public List<Image> findImagesByProductId(@Param("productId") long productId);
	
	@Query("SELECT p.subProducts FROM Product p WHERE p.id = :productId")
	public List<Product> findSubProductsByProductId(@Param("productId") long productId);
}
