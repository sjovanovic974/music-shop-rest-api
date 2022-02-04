package sasa.jovanovic.musicshop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category doesn't exist!");
            return new NotFoundException("Category doesn't exist!");
        });
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        productCategoryRepository.findById(productCategory.getId())
        .orElseThrow(() -> {
            log.error("Product was not found!");
            return new NotFoundException("Product was not found!");
        });

        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        productCategoryRepository.findById(productCategory.getId())
            .orElseThrow(() -> {
                log.error("Product was not found!");
                return new NotFoundException("Product was not found!");
            });

        productCategoryRepository.delete(productCategory);
    }

    @Override
    public void deleteProductCategoryById(Long id) {
        productCategoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product was not found!");
                    return new NotFoundException("Product was not found!");
                });

        productCategoryRepository.deleteById(id);
    }
}
