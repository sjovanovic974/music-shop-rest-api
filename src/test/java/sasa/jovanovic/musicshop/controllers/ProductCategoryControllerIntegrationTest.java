package sasa.jovanovic.musicshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductCategoryService;

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

@WebMvcTest(controllers = ProductCategoryController.class)
class ProductCategoryControllerIntegrationTest {


    @MockBean
    private ProductCategoryService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ProductCategory> categories;

    @BeforeEach
    void setUp() {
        ProductCategory cd = new ProductCategory();
        cd.setId(1L);
        cd.setCategoryName("CD");

        ProductCategory lp = new ProductCategory();
        lp.setId(2L);
        lp.setCategoryName("LP");

        categories = new ArrayList<>();

        categories = new ArrayList<>();
        categories.add(cd);
        categories.add(lp);
    }

    @Test
    @DisplayName("Should return all categories")
    void getProductCategories() throws Exception {

        when(service.getProductCategories()).thenReturn(categories);

        mockMvc.perform(get("/products/categories")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()", is(categories.size())));

        verify(service).getProductCategories();
    }

    @Test
    @DisplayName("Should return category by given id")
    void getProductCategoryById() throws Exception {

        when(service.getProductCategoryById(anyLong())).thenReturn(categories.get(1));

        mockMvc.perform(get("/products/categories/1")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.categoryName").value("LP"));

        verify(service).getProductCategoryById(anyLong());
    }

    @Test
    @DisplayName("Should save category")
    void saveProductCategory() throws Exception {
        when(service.saveProductCategory(any(ProductCategory.class))).thenReturn(categories.get(0));

        mockMvc.perform(post("/products/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categories.get(0))))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.categoryName").value("CD"));

        verify(service).saveProductCategory(any(ProductCategory.class));
    }

    @Test
    @DisplayName("Should update given category")
    void updateProductCategory() throws Exception {
        ProductCategory lp = categories.get(1);
        lp.setCategoryName("vinyl");

        when(service.updateProductCategory(any(ProductCategory.class))).thenReturn(lp);

        mockMvc.perform(put("/products/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(lp)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.categoryName").value("vinyl"));

        verify(service).updateProductCategory(any(ProductCategory.class));
    }

    @Test
    @DisplayName("Should delete product category by given object")
    void deleteProductCategory() throws Exception {

        mockMvc.perform(delete("/products/categories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categories.get(0))))
                .andExpect(status().isOk())
                .andDo(print());

        ArgumentCaptor<ProductCategory> captor = ArgumentCaptor.forClass(ProductCategory.class);
        verify(service).deleteProductCategory(captor.capture());

        assertThat(captor.getValue().getCategoryName()).isEqualTo("CD");
    }

    @Test
    @DisplayName("Should delete product category by given id")
    void deleteProductCategoryById() throws Exception {

        mockMvc.perform(delete("/products/categories/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(service).deleteProductCategoryById(captor.capture());

        assertThat(captor.getValue()).isEqualTo(1L);
    }
}