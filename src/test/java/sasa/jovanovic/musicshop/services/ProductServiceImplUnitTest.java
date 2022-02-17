package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplUnitTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductServiceImpl service;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private List<Product> tempList;

    @BeforeEach
    public void initEach() {
        tempList = new ArrayList<>();

        ProductCategory cd = new ProductCategory();
        cd.setId(1L);

        ProductCategory lp = new ProductCategory();
        lp.setId(2L);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setCategory(cd);
        product1.setName("Iron Maiden: Early Years");
        product1.setSku("CD000011");

        Product product2= new Product();
        product2.setId(2L);
        product2.setCategory(lp);
        product2.setName("Metallica: Master of Puppets");
        product2.setSku("LP000022");

        tempList.add(product1);
        tempList.add(product2);
    }

    @Test
    @DisplayName("Should pass if returns exact same number of products as contained in tempList")
    void getAllProducts() {

        when(productRepository.findAll()).thenReturn(tempList);

        List<Product> returnedProducts = service.getAllProducts();

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(returnedProducts.size(), tempList.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a page of products")
    void getProducts() {

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());

        when(productRepository.findAll(page)).thenReturn(products);

        Page<Product> returnedProducts = productRepository.findAll(page);

        Assertions.assertNotNull(returnedProducts);
        Assertions.assertEquals(returnedProducts.getContent().size(), products.getContent().size());

        verify(productRepository, times(1)).findAll(page);
    }


    @DisplayName("Should find a page of products belonging to the given category")
    @Test
    void findByCategoryId() {

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());

        when(productRepository.findByCategoryId(1L, page)).thenReturn(products);

        Page<Product> returnedProducts = service.findByCategoryId(1L, page);

        Assertions.assertEquals(returnedProducts.getContent().get(0).getCategory().getCategoryName(),
                tempList.get(0).getCategory().getCategoryName());

        verify(productRepository, times(1)).findByCategoryId(1L, page);
    }

    @Test
    @DisplayName("Should find product by given name")
    void findByNameContaining() {

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(tempList, page, tempList.size());
        String argument = "Maiden";

        when(productRepository.findByNameContainingIgnoreCase(argument, page)).thenReturn(products);

        Page<Product> returnedProducts = service.findByNameContainingIgnoreCase(argument, page);
        String returnedProductName = returnedProducts.getContent().get(0).getName();

        Assertions.assertTrue(returnedProductName.contains(argument));
        verify(productRepository, times(1)).findByNameContainingIgnoreCase(argument, page);
    }

    @Test
    @DisplayName("Should find product by given id")
    void getProductById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(tempList.get(0)));
        Product returnedProduct = service.getProductById(anyLong());

        Assertions.assertEquals(returnedProduct.getId(), tempList.get(0).getId());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw NotFoundException if product was not found")
    void productNotFoundById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            service.getProductById(99L);
        });

        Assertions.assertTrue(exception.getMessage().contains("Product was not found!"));
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should save product")
    void saveProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(tempList.get(0));

        Product savedProduct = service.saveProduct(tempList.get(0));

        Assertions.assertNotNull(savedProduct);
        verify(productRepository, times(1)).save(productCaptor.capture());

        Assertions.assertEquals(productCaptor.getValue().getSku(), tempList.get(0).getSku());
    }

    @Test
    @DisplayName("Should update product")
    void updateProduct() {

        when(productRepository.save(any(Product.class))).thenReturn(tempList.get(0));
        Product savedProduct = service.saveProduct(tempList.get(0));

        savedProduct.setName("Iron Maiden: Live after Death");

        when(productRepository.findById(savedProduct.getId())).thenReturn(Optional.of(savedProduct));
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product updatedProduct = service.updateProduct(savedProduct);

        Assertions.assertEquals(savedProduct.getId(), updatedProduct.getId());
        Assertions.assertEquals(savedProduct.getName(), updatedProduct.getName());

        verify(productRepository, times(1)).findById(anyLong());
        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    @DisplayName("Should delete product by given object")
    void deleteProduct() {

        when(productRepository.findById(tempList.get(0).getId())).thenReturn(Optional.of(tempList.get(0)));

        service.deleteProduct(tempList.get(0));
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    @DisplayName("Should delete product by given id")
    void deleteProductById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(tempList.get(0)));

        service.deleteProductById(anyLong());
        verify(productRepository, times(1)).deleteById(anyLong());
    }
}