package sasa.jovanovic.musicshop;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MusicShopApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	private GlobalExceptionHandler exceptionHandler;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductService productService;


	@Test
	void contextLoads() {
		assertThat(productController).isNotNull();
		assertThat(exceptionHandler).isNotNull();
		assertThat(productCategoryRepository).isNotNull();
		assertThat(productRepository).isNotNull();
		assertThat(productCategoryService).isNotNull();
		assertThat(productService).isNotNull();
	}

	@Test
	void getMediumPriceProducts(){
		List<Product> list =
				productRepository.getMediumPricedProducts(new BigDecimal("20.00"), new BigDecimal("40.00"));

		list.forEach(item -> System.out.println(item.getName() + ": " + item.getUnitPrice()));
	}

	@Test
	void getMostExpensiveProduct(){
		Product product = productRepository.getMostExpensiveProduct();

		System.out.println(product.getSku() + " - " + product.getName() + " - " + product.getUnitPrice());
	}
}
