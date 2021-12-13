package sasa.jovanovic.musicshop.services;

import sasa.jovanovic.musicshop.models.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategories();

    ProductCategory getProductCategoryById(Long id);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    ProductCategory updateProductCategory(ProductCategory productCategory);

    void deleteProductCategory(ProductCategory productCategory);

    void deleteProductCategoryById(Long id);
}
