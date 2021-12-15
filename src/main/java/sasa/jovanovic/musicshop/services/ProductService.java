package sasa.jovanovic.musicshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sasa.jovanovic.musicshop.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Page<Product> getProducts(Pageable page);

    Page<Product> findByCategoryId(Long id, Pageable page);

    Page<Product> findByNameContaining(String name, Pageable page);

    Product getProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProductById(Long id);
}
