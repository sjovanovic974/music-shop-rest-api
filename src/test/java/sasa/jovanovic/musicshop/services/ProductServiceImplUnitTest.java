package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;
import sasa.jovanovic.musicshop.repos.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplUnitTest {

    @InjectMocks
    ProductServiceImpl service;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductCategoryRepository productCategoryRepository;

    Product product;
    List<Product> tempList;

    @BeforeEach
    public void setUp() {
        //service = new ProductServiceImpl(productRepository, productCategoryRepository);
        product = new Product();
        tempList = new ArrayList<>();
    }

    @Test
    void getAllProducts() {
        tempList.add(product);

        when(productRepository.findAll()).thenReturn(tempList);

        List<Product> returnedProducts = service.getAllProducts();

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(returnedProducts.size(), tempList.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProducts() {
        tempList.add(product);

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());

        when(productRepository.findAll(page)).thenReturn(products);

        Page<Product> returnedProducts = productRepository.findAll(page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(returnedProducts.getContent().size(), products.getContent().size());
        verify(productRepository, times(1)).findAll(page);
    }

    @Test
    void findByCategoryId() {

        ProductCategory category = new ProductCategory();
        Long id = 1L;
        category.setId(id);

        product.setCategory(category);
        tempList.add(product);

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());

        when(productRepository.findByCategoryId(id, page)).thenReturn(products);

        Page<Product> returnedProducts = service.findByCategoryId(id, page);

        Assertions.assertEquals(returnedProducts.getContent().get(0).getCategory().getCategoryName(),
                product.getCategory().getCategoryName());
        verify(productRepository, times(1)).findByCategoryId(id, page);
    }

    @Test
    void findByNameContaining() {
        product.setName("Iron Maiden: Early Years");
        tempList.add(product);

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());
        String argument = "Maiden";

        when(productRepository.findByNameContaining(argument, page)).thenReturn(products);

        Page<Product> returnedProducts = productRepository.findByNameContaining(argument, page);

        String returnedProductName = returnedProducts.getContent().get(0).getName();

        Assertions.assertTrue(returnedProductName.contains(argument));
        verify(productRepository, times(1)).findByNameContaining(argument, page);
    }

    @Test
    void getProductById() {
        Long id = 5L;
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product returnedProduct = service.getProductById(id);

        Assertions.assertEquals(returnedProduct.getId(), product.getId());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void productNotFoundById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> {
            service.getProductById(anyLong());
        });

        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void saveProduct() {
        product.setId(1L);

        when(productRepository.save(any())).thenReturn(product);

        Product savedProduct = service.saveProduct(product);

        Assertions.assertNotNull(savedProduct);
        Assertions.assertTrue(Objects.equals(savedProduct.getId(), product.getId()));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void updateProduct() {
        product.setId(99L);
        product.setName("test");

        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = service.saveProduct(product);

        savedProduct.setName("new test");

        when(productRepository.findById(savedProduct.getId())).thenReturn(Optional.of(savedProduct));
        when(productRepository.save(savedProduct)).thenReturn(savedProduct);

        Product updatedProduct = service.updateProduct(savedProduct);

        Assertions.assertEquals(savedProduct.getId(), updatedProduct.getId());
        Assertions.assertEquals(product.getName(), updatedProduct.getName());

        verify(productRepository, times(2)).save(any());
    }

    @Test
    void deleteProduct() {
        product.setId(1L);
        product.setName("test product");

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        service.deleteProduct(product);
        verify(productRepository, times(1)).delete(any());
    }

    @Test
    void deleteProductById() {
        product.setId(1L);
        product.setName("test product");

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        service.deleteProductById(product.getId());
        verify(productRepository, times(1)).deleteById(anyLong());
    }
}