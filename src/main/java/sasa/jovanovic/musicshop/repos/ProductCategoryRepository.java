package sasa.jovanovic.musicshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import sasa.jovanovic.musicshop.models.ProductCategory;

@Repository
@CrossOrigin
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
