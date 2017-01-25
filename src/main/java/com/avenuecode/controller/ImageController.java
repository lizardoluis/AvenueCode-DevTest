package com.avenuecode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Response;
import com.avenuecode.api.service.ImageService;
import com.avenuecode.core.exception.ProductException;

@RestController
public class ImageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;
	
	/*
	 * Get all images.
	 */
	@RequestMapping(value="/images", method=RequestMethod.GET)
	public ResponseEntity<List<Image>> getAllImage(){
    	logger.info("Returning all the Image´s");
		return new ResponseEntity<List<Image>>(imageService.getAllImages(), HttpStatus.OK);
	}
	
	/*
	 * Get specific image
	 */
    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
	public ResponseEntity<Image> getImageById(@PathVariable("id") long id) throws ProductException{
    	logger.info("Image id to return " + id);
    	Image image = imageService.getImageById(id);
    	if (image == null || image.getId() <= 0){
            throw new ProductException("Image doesn´t exist");
    	}
		return new ResponseEntity<Image>(image, HttpStatus.OK);
	}

	/*
	 * Delete
	 */
    @RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeImageById(@PathVariable("id") long id) throws ProductException{
    	logger.info("Image id to remove " + id);
    	Image image = imageService.getImageById(id);
    	if (image == null || image.getId() <= 0){
            throw new ProductException("Image to delete doesn´t exist");
    	}
		imageService.removeImage(image);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Image has been deleted"), HttpStatus.OK);
	}
    
	/*
	 * Create
	 */
    @RequestMapping(value = "/images", method = RequestMethod.POST)
   	public ResponseEntity<Image> saveImage(@RequestBody Image payload) throws ProductException{
    	logger.info("Payload to save " + payload);
    	if (payload.getId() > 0){
            throw new ProductException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<Image>(imageService.saveImage(payload), HttpStatus.OK);
   	}
    
	/*
	 * Update
	 */
    @RequestMapping(value = "/images", method = RequestMethod.PATCH)
   	public ResponseEntity<Image>  updateImage(@RequestBody Image payload) throws ProductException{
    	logger.info("Payload to update " + payload);
    	Image image = imageService.getImageById(payload.getId());
    	if (image == null || image.getId() <= 0){
            throw new ProductException("Image to update doesn´t exist");
    	}
		return new ResponseEntity<Image>(imageService.saveImage(payload), HttpStatus.OK);
   	}
	
}
