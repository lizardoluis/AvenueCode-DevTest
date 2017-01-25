package com.avenuecode.api.service;

import java.util.List;

import com.avenuecode.api.entity.Image;

/**
 * This interface describes the operations supported by the application to
 * manage images.
 *
 * @author Luis Eduardo Oliveira Lizardo
 *
 */
public interface ImageService {

	/**
	 * Get a list of all images stored in the database.
	 * 
	 * @return list of images.
	 */
	public List<Image> getAllImages();

	/**
	 * Get an image with the specified id.
	 * 
	 * @param id
	 *            of the image.
	 * @return an image.
	 */
	public Image getImageById(long id);

	/**
	 * Saves an image in the database.
	 * 
	 * @param image
	 *            to be saved.
	 * @return the image with the generated id.
	 */
	public Image saveImage(Image image);

	/**
	 * Deletes the specified image from the database.
	 * 
	 * @param image
	 *            to be deleted.
	 */
	public void removeImage(Image image);
}
