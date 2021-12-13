package sasa.jovanovic.musicshop.services;

import sasa.jovanovic.musicshop.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProductById(Long id);
}
