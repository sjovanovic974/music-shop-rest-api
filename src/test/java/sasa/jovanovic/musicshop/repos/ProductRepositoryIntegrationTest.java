package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("DEV")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;


    @Test
    @DisplayName("Should load entityManager and DataSource beans")
    void contextLoads() {
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(dataSource);
    }

    @Test
    @DisplayName("Should find products by given category")
    void findByCategoryId() {

        int expected = 3;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(2L, page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    @DisplayName("Should return empty page")
    void findNotByCategoryId() {

        int expected = 0;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(99L, page);


        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    @DisplayName("Should find by name containing given string")
    void findByNameContaining() {
        int expected = 1;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByNameContainingIgnoreCase("Ramones", page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    @DisplayName("Should find the last entry in a given category")
    void findLastEntryByCategory() {
        ProductCategory cd = categoryRepository.getById(1L);
        ProductCategory vinyl = categoryRepository.getById(2L);


        Product product = repository.findTopByCategoryOrderByIdDesc(
                cd).orElse(null);

        assertThat(product).isNotNull();
        assertThat(product.getSku()).isEqualTo("CD000010");

        Product product2 = repository.findTopByCategoryOrderByIdDesc(
                vinyl).orElse(null);

        assertThat(product2).isNotNull();
        assertThat(product2.getSku()).isEqualTo("LP000003");
    }

    @Test
    @DisplayName("Should count all entries in product table")
    void totalEntries() {
        long expected = repository.count();

        List<Product> returnedProducts = repository.findAll();

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.size());
    }
}