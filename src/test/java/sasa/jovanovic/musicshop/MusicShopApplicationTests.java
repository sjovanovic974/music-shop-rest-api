package sasa.jovanovic.musicshop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sasa.jovanovic.musicshop.controllers.ProductController;
import sasa.jovanovic.musicshop.errorhandling.GlobalExceptionHandler;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;
import sasa.jovanovic.musicshop.repos.ProductRepository;
import sasa.jovanovic.musicshop.services.ProductCategoryService;
import sasa.jovanovic.musicshop.services.ProductService;

@SpringBootTest
class MusicShopApplicationTests {

	@Autowired
	ProductController productController;

	@Autowired
	GlobalExceptionHandler exceptionHandler;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductService productService;


	@Test
	void contextLoads() {
		Assertions.assertThat(productController).isNotNull();
		Assertions.assertThat(exceptionHandler).isNotNull();
		Assertions.assertThat(productCategoryRepository).isNotNull();
		Assertions.assertThat(productRepository).isNotNull();
		Assertions.assertThat(productCategoryService).isNotNull();
		Assertions.assertThat(productService).isNotNull();
	}

}
