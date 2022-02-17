package sasa.jovanovic.musicshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerIntegrationTest {

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<Product> productsList = new ArrayList<>();
    private Product p1;
    private Product p2;

    @BeforeEach
    void setUp() {
        ProductCategory cd = new ProductCategory();
        cd.setId(1L);
        cd.setCategoryName("CD");

        ProductCategory vinyl = new ProductCategory();
        vinyl.setId(2L);
        vinyl.setCategoryName("vinyl");

        p1 = new Product();
        p1.setId(1L);
        p1.setCategory(cd);
        p1.setName("Metallica: Master of puppets");
        p1.setActive(true);
        p1.setUnitsInStock(10);
        p1.setSku("CD000005");

        p2 = new Product();
        p2.setId(2L);
        p2.setCategory(vinyl);
        p2.setName("Iron Maiden: Live After Death");
        p2.setImageUrl("path");
        p2.setActive(true);
        p2.setUnitsInStock(25);
        p2.setSku("LP000001");

        productsList.add(p1);
        productsList.add(p2);
    }

    @Test
    @DisplayName("Should return all products")
    void getProducts() throws Exception {

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(productsList, page, productsList.size());

        int size = products.getContent().size();

        when(service.getProducts(any(Pageable.class))).thenReturn(products);

        mockMvc.perform(get("/products")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content.size()", is(size)));

        verify(service).getProducts(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return product by given id")
    void getById() throws Exception {

        when(service.getProductById(anyLong())).thenReturn(p1);

        mockMvc.perform(get("/products/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Metallica: Master of puppets"))
                .andExpect(jsonPath("$.category.categoryName").value("CD"));

        verify(service).getProductById(anyLong());
    }

    @Test
    @DisplayName("Should save product")
    void saveProduct() throws Exception {

        when(service.saveProduct(any(Product.class))).thenReturn(p2);

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(p2)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Iron Maiden: Live After Death"))
                .andExpect(jsonPath("$.category.categoryName").value("vinyl"))
                .andExpect(jsonPath("$.active").value("true"))
                .andExpect(jsonPath("$.unitsInStock").value("25"))
                .andExpect(jsonPath("$.sku").value("LP000001"));

        verify(service).saveProduct(any(Product.class));
    }

    @Test
    @DisplayName("Should return a bad request")
    void saveProductBadRequest() throws Exception {

        p2 = null;

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(p2)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("Should update given product")
    void updateProduct() throws Exception {

        p2.setName("Pantera: Vulgar Display of Power");

        when(service.updateProduct(any(Product.class))).thenReturn(p2);

        mockMvc.perform(put("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(p2)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Pantera: Vulgar Display of Power"));

        verify(service).updateProduct(any(Product.class));
    }

    @Test
    @DisplayName("Should delete product by given object")
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(p2)))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(service).deleteProduct(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo("Iron Maiden: Live After Death");
    }

    @Test
    @DisplayName("Should delete product by given id")
    void deleteProductById() throws Exception {
        mockMvc.perform(delete("/products/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(service).deleteProductById(captor.capture());

        assertThat(captor.getValue()).isEqualTo(1L);
    }
}