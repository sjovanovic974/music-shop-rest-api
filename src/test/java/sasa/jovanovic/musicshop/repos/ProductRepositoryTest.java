package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sasa.jovanovic.musicshop.models.Product;

import java.util.List;

@DataJpaTest
class ProductRepositoryTest {

    private final ProductRepository repository;

    List<Product> tempList;

    @Autowired
    ProductRepositoryTest(ProductRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    public void setUp() {
        tempList = repository.findAll();
    }

    @Test
    void findByCategoryId() {
        // NE valja
        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());

        Page<Product> returnedProducts = repository.findAll(page);

        Assertions.assertNotNull(returnedProducts);
        System.out.println("Temp List size: " + tempList.size());
        Assertions.assertEquals(returnedProducts.getContent().size(), tempList.size());
    }

    @Test
    void findByNameContaining() {
    }
}