package com.avenuecode.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.avenuecode.api.entity.Product;
import com.avenuecode.core.management.ProductManager;
import com.avenuecode.core.persistence.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductManager productService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllProducts(){
		List<Product> productList = new ArrayList<Product>();
		productList.add(new Product(1, "PC", "IBM PC"));
		productList.add(new Product(2,"Smartphone","Android"));
		productList.add(new Product(3,"iPad","Apple design"));
		when(productRepository.findAll()).thenReturn(productList);
		
		List<Product> result = productService.getAllProducts();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testGetProductById(){
		Product product = new Product(1, "PC", "IBM PC");
		when(productRepository.findOne(1L)).thenReturn(product);
	
		Product result = productService.getProductById(1);
		
		assertEquals(1, result.getId());
		assertEquals("PC", result.getName());
		assertEquals("IBM PC", result.getDescription());
	}
	
	@Test
	public void saveProduct(){
		Product product = new Product(8, "iPad", "Apple");
		when(productRepository.save(product)).thenReturn(product);
		
		Product result = productService.saveProduct(product);
		
		assertEquals(8, result.getId());
		assertEquals("iPad", result.getName());
		assertEquals("Apple", result.getDescription());
	}
	
	@Test
	public void removeToDo(){
		Product product = new Product(8, "iPad", "Apple");
		productService.removeProduct(product);
        verify(productRepository, times(1)).delete(product);
	}

}

