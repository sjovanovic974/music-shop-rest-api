package sasa.jovanovic.musicshop.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.repos.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        Page<Product> products = productRepository.findByCategoryId(id, page);

        if (products.getSize() == 0) {
            log.error("No such category found!");
            throw new NotFoundException("No such category found!");
        }

        return products;
    }

    @Override
    public Page<Product> findByNameContainingIgnoreCase(String name, Pageable page) {

        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, page);

        if (products.getSize() == 0) {
            log.error("No products found!");
            throw new NotFoundException("No products found!");
        }

        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> {
                    log.error("Product was not found!");
                    return new NotFoundException("Product was not found!");
                });
    }

    @Override
    public Product saveProduct(Product product) {

        if(product.getSku() == null) {
            String nextSku = product.getCategory().getCategoryName() + "000001";

            Product lastSaved = productRepository.findTopByCategoryOrderByIdDesc(product.getCategory()).
                    orElse(null);

            if (lastSaved != null) {
                String categoryName = product.getCategory().getCategoryName();
                String currentSkuNumber = lastSaved.getSku().substring(2);

                nextSku = getNextSku(categoryName, currentSkuNumber);
            }

            product.setSku(nextSku);
        }
        return productRepository.save(product);
    }

    private String getNextSku(String categoryName, String currentSkuNumber) {
        Integer number = (Integer.parseInt(currentSkuNumber)) + 1;
        return categoryName + String.format("%06d", number);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> {
                    log.error("Product was not found!");
                    return new NotFoundException("Product was not found!");
                });

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> {
                    log.error("Product was not found!");
                    return new NotFoundException("Product was not found!");
                });

        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product was not found!");
                    return new NotFoundException("Product was not found!");
                });

        productRepository.deleteById(id);
    }
}
