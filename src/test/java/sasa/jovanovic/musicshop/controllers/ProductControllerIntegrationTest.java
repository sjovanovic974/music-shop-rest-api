package sasa.jovanovic.musicshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private ProductController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private List<Product> productsList = new ArrayList<>();
    private ProductCategory cd;
    private ProductCategory vinyl;
    private Product p1;
    private Product p2;

    @BeforeEach
    void setUp() {
        cd = new ProductCategory();
        Long cdId = 1L;
        cd.setId(cdId);
        cd.setCategoryName("CD");

        vinyl = new ProductCategory();
        Long vinylId = 2L;
        vinyl.setId(vinylId);
        vinyl.setCategoryName("vinyl");

        p1 = new Product();
        p1.setId(1L);
        p1.setCategory(cd);
        p1.setName("Metallica: Master of puppets");

        p2 = new Product();
        p2.setId(2L);
        p2.setCategory(vinyl);
        p2.setName("Iron Maiden: Live After Death");
        p2.setImageUrl("path");

        productsList.add(p1);
        productsList.add(p2);
    }

    @Test
    void getProducts() throws Exception {

        Pageable page = PageRequest.of(0, 10);
        Page<Product> products = new PageImpl<>(productsList, page, productsList.size());

        int size = products.getContent().size();

        when(service.getProducts(any(Pageable.class))).thenReturn(products);

        mockMvc.perform(get("/products")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content", hasSize(size)));
    }

    @Test
    void getById() throws Exception {

        when(service.getProductById(anyLong())).thenReturn(p1);

        mockMvc.perform(get("/products/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Metallica: Master of puppets"))
                .andExpect(jsonPath("$.category.categoryName").value("CD"))
                .andExpect(status().isOk());

    }

    @Test
    void saveProduct() throws Exception {
        p2.setActive(true);
        p2.setUnitsInStock(25);
        p2.setSku("vn1005");

        when(service.saveProduct(any(Product.class))).thenReturn(p2);

        mockMvc.perform(post("/products")
                        .content(new ObjectMapper().writeValueAsString(p2))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Iron Maiden: Live After Death"))
                .andExpect(jsonPath("$.category.categoryName").value("vinyl"))
                .andExpect(jsonPath("$.active").value("true"))
                .andExpect(jsonPath("$.unitsInStock").value("25"))
                .andExpect(jsonPath("$.sku").value("vn1005"));
    }

    @Test
    void saveProductBadRequest() throws Exception {

        p2.setCategory(null);

        when(service.saveProduct(any(Product.class))).thenReturn(p2);

        mockMvc.perform(post("/products")
                        .content(new ObjectMapper().writeValueAsString(p2))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void updateProduct() throws Exception {

        p2.setName("Pantera: Vulgar Display of Power");

        when(service.updateProduct(any(Product.class))).thenReturn(p2);

        mockMvc.perform(put("/products")
                        .content(new ObjectMapper().writeValueAsString(p2))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Pantera: Vulgar Display of Power"));
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products")
                .content(new ObjectMapper().writeValueAsString(p2))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProductById() throws Exception {
        mockMvc.perform(delete("/products/1")
                .content(new ObjectMapper().writeValueAsString(p1))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}