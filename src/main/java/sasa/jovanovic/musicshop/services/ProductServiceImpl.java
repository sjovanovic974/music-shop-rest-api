package sasa.jovanovic.musicshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;
import sasa.jovanovic.musicshop.repos.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
           ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public Page<Product> findByCategoryId(Long id, Pageable page) {
        if(!productCategoryRepository.existsById(id)){
            throw new NotFoundException("Category not found!");
        }
        return productRepository.findByCategoryId(id, page);
    }

    @Override
    public Page<Product> findByNameContaining(String name, Pageable page) {
        return productRepository.findByNameContaining(name, page);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product was not found!"));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        if (product == null || !productRepository.existsById(product.getId())) {
            throw new NotFoundException("Product was not found!");
        }
        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product was not found!");
        }
        productRepository.deleteById(id);
    }
}
