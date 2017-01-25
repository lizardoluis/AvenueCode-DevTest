/**
 * 
 */
package com.avenuecode.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * This entity represents a Product.
 * 
 * @author Luis Eduardo Oliveira Lizardo
 */

@Entity
public class Product {

	@Id
	@GeneratedValue
	private long id;	
	
	private String name;
	private String description;	
	
	@OneToMany( targetEntity=Image.class, cascade = CascadeType.ALL, orphanRemoval=true )
	private List<Image> images;	
	
	@OneToMany( targetEntity=Product.class, cascade = CascadeType.ALL, orphanRemoval=true )
	private List<Product> subProducts;
	
	public Product() {
		super();
	}

	public Product(long id, String name, String description) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public void addImage(Image image){
		if(this.images == null){
			this.images = new ArrayList<Image>();
		}
		this.images.add(image);
	}

	public List<Product> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(List<Product> subProducts) {
		this.subProducts = subProducts;
	}
	
	public void addSubProduct(Product subProduct){
		if(this.subProducts == null){
			this.subProducts = new ArrayList<Product>();
		}
		this.subProducts.add(subProduct);
	}
}