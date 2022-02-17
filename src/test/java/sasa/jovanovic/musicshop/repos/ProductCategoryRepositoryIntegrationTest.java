package sasa.jovanovic.musicshop.repos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sasa.jovanovic.musicshop.models.ProductCategory;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("DEV")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductCategoryRepositoryIntegrationTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    @DisplayName("Should confirm that new category was just added")
    void addCategory() {
        long countBefore = repository.count();

        ProductCategory blueRay = new ProductCategory();
        blueRay.setCategoryName("Blue Ray");
        repository.save(blueRay);

        long countAfter = repository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }
}