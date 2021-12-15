package sasa.jovanovic.musicshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<Product> getProducts(Pageable page){
        return productService.getProducts(page);
    }

    @GetMapping("/category/{id}")
    public Page<Product> getProducts(@PathVariable("id") Long id, Pageable page){
        return productService.findByCategoryId(id, page);
    }

    @GetMapping("/search")
    public Page<Product> getProducts(@RequestParam("name") String name, Pageable page){
        return productService.findByNameContaining(name, page);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody Product product){
        productService.deleteProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        productService.deleteProductById(id);
    }
}
