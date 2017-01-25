/**
 * 
 */
package com.avenuecode.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This entity represents a Image.
 * 
 * @author Luis Eduardo Oliveira Lizardo
 */

@Entity
public class Image {

	@Id
	@GeneratedValue
	private long id;	
	private String type;	
	private String path;
	
	public Image(){
		super();
	}	
	
	public Image(long id, String type, String path) {
		super();
		this.setId(id);
		this.setType(type);
		this.setPath(path);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	
}