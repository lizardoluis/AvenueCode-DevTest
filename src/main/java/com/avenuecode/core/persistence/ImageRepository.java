package com.avenuecode.core.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenuecode.api.entity.Image;

@Repository("imageRepository")
public interface ImageRepository extends JpaRepository<Image, Long>{

}
