package sasa.jovanovic.musicshop.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sasa.jovanovic.musicshop.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(Long id, Pageable page);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable page);
}
