package sasa.jovanovic.musicshop.services;

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
            throw new NotFoundException("No such category found!");
        }

        return products;
    }

    @Override
    public Page<Product> findByNameContainingIgnoreCase(String name, Pageable page) {

        Page<Product> products = productRepository.findByNameContainingIgnoreCase(name, page);

        if (products.getSize() == 0) {
            throw new NotFoundException("No products found!");
        }

        return products;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product was not found!"));
    }

    @Override
    public Product saveProduct(Product product) {

        Product lastSaved = productRepository.findTopByCategoryOrderByIdDesc(product.getCategory()).
                orElse(null);

        String nextSku = product.getCategory().getCategoryName() + "000001";

        if(lastSaved != null) {
            String currentSku = lastSaved.getSku().substring(2);
            Integer number = (Integer.parseInt(currentSku)) + 1;
            nextSku = product.getCategory().getCategoryName() + String.format("%06d", number);
        }

        product.setSku(nextSku);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product was not found!"));

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new NotFoundException("Product was not found!"));

        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product was not found!"));

        productRepository.deleteById(id);
    }
}
