package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.math.BigDecimal;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        ProductCategory c1 = new ProductCategory();
        c1.setId(1L);
        c1.setCategoryName("CD");

        ProductCategory c2 = new ProductCategory();
        c2.setId(1L);
        c2.setCategoryName("CD");

        ProductCategory c3 = new ProductCategory();
        c3.setId(2L);
        c3.setCategoryName("vinyl");

        Product p1 = new Product();
        p1.setName("Metallica: Master of Puppets");
        p1.setCategory(c1);
        p1.setSku("cd00001");
        p1.setActive(true);
        p1.setUnitsInStock(15);
        p1.setUnitPrice(new BigDecimal(15));

        Product p2 = new Product();
        p2.setName("Metallica: Ride the Lightning");
        p2.setCategory(c2);
        p2.setSku("cd00002");
        p2.setActive(false);
        p2.setUnitsInStock(0);
        p2.setUnitPrice(new BigDecimal(15));

        Product p3 = new Product();
        p3.setName("Iron Maiden: Killers");
        p3.setCategory(c3);
        p3.setSku("vn00001");
        p3.setActive(true);
        p3.setUnitsInStock(8);
        p3.setUnitPrice(new BigDecimal(25));

        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);

        repository.save(p1);
        repository.save(p2);
        repository.save(p3);
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(entityManager);
        Assertions.assertNotNull(dataSource);
    }

    @Test
    void findByCategoryId() {

        int expected = 2;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(1L, page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }

    @Test
    void findNotByCategoryId() {
        int expected = 0;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByCategoryId(99L, page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());

    }

    @Test
    void findByNameContaining() {
        int expected = 2;

        Pageable page = PageRequest.of(0, 10);

        Page<Product> returnedProducts = repository.findByNameContaining("Met", page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(expected, returnedProducts.getContent().size());
    }
}