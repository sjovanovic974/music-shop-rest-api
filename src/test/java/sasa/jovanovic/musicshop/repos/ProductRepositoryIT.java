package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class ProductRepositoryIT {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(dataSource);
    }

    @Test
    void findByCategoryId() {

        int expected = 3;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(2L, page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    void findNotByCategoryId() {
        int expected = 0;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(99L, page);

        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    void findByNameContaining() {
        int expected = 1;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByNameContainingIgnoreCase("Ramones", page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    void totalEntries() {
        long expected = repository.count();

        List<Product> returnedProducts = repository.findAll();

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.size());
    }

    @Test
    void addEntry() {
        long countBefore = categoryRepository.count();

        ProductCategory blueRay = new ProductCategory();
        blueRay.setCategoryName("Blue Ray");
        categoryRepository.save(blueRay);

        long countAfter = categoryRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
}