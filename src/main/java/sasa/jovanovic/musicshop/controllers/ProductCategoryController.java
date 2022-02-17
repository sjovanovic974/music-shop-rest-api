package sasa.jovanovic.musicshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductCategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getProductCategories();
    }

    @GetMapping("/{id}")
    public ProductCategory getProductCategoryById(@PathVariable("id") Long id) {
        return productCategoryService.getProductCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductCategory saveProductCategory(@Valid @RequestBody ProductCategory productCategory) {
        return productCategoryService.saveProductCategory(productCategory);
    }

    @PutMapping
    public ProductCategory updateProductCategory(@Valid @RequestBody ProductCategory productCategory){
        return productCategoryService.updateProductCategory(productCategory);
    }

    @DeleteMapping
    public void deleteProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.deleteProductCategory(productCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteProductCategoryById(@PathVariable("id") Long id){
        productCategoryService.deleteProductCategoryById(id);
    }
}
