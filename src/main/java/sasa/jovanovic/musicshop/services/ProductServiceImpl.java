package sasa.jovanovic.musicshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.exceptions.NotFoundException;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.repos.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public Page<Product> findByCategoryId(Long id, Pageable page) {
        return productRepository.findByCategoryId(id, page);
    }

    @Override
    public Page<Product> findByNameContaining(String name, Pageable page){
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
        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
