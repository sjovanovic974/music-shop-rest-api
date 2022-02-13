package sasa.jovanovic.musicshop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sasa.jovanovic.musicshop.controllers.ProductController;
import sasa.jovanovic.musicshop.errorhandling.GlobalExceptionHandler;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;
import sasa.jovanovic.musicshop.repos.ProductRepository;
import sasa.jovanovic.musicshop.services.ProductCategoryService;
import sasa.jovanovic.musicshop.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

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

	@Test
	void getMediumPriceProducts(){
		List<Product> list =
				productRepository.getMediumPricedProducts(new BigDecimal("20.00"), new BigDecimal("45.00"));

		list.stream().forEach(item -> System.out.println(item.getName() + ": " + item.getUnitPrice()));
	}

	@Test
	void getMostExpensiveProduct(){
		Product product = productRepository.getMostExpensiveProduct();

		System.out.println(product.getSku() + " - " + product.getName() + " - " + product.getUnitPrice());
	}
}
