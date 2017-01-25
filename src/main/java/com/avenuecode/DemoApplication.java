package com.avenuecode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.avenuecode.api.entity.Image;
import com.avenuecode.api.entity.Product;
import com.avenuecode.core.persistence.ProductRepository;

@SpringBootApplication
public class DemoApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(ProductRepository productRepository) {
		return (args) -> {
			
			Product motorola = new Product(1, "Motorola Moto X Play", "Android");
			motorola.addImage(new Image(1, "Case", "/images/case.jpg"));
			motorola.addImage(new Image(2, "Front", "/images/front.jpg"));
			motorola.addImage(new Image(3, "Side", "/images/side.jpg"));
			
			productRepository.save(motorola);
			productRepository.save(new Product(2, "iPhone 7", "Ios"));
			productRepository.save(new Product(3, "Windows Phone", "Windows"));
			productRepository.save(new Product(4, "Google Nexus", "Android"));
			logger.info("The sample data has been generated");
		};
	}
}
