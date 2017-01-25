package com.avenuecode.core.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.service.ImageService;
import com.avenuecode.core.persistence.ImageRepository;

@Service("imageService")
public class ImageManager implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public List<Image> getAllImages() {
		return imageRepository.findAll();
	}

	@Override
	public Image getImageById(long id) {
		return imageRepository.findOne(id);
	}

	@Override
	public Image saveImage(Image image) {
		return imageRepository.save(image);
	}

	@Override
	public void removeImage(Image image) {
		imageRepository.delete(image);
	}
	

}
