package sasa.jovanovic.musicshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return savedProduct;
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return savedProduct;
    }
}
