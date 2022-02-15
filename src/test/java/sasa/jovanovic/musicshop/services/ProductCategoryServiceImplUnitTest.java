package sasa.jovanovic.musicshop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sasa.jovanovic.musicshop.errorhandling.NotFoundException;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.repos.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceImplUnitTest {

    @Mock
    ProductCategoryRepository repository;

    @InjectMocks
    ProductCategoryServiceImpl service;

    @Captor
    ArgumentCaptor<ProductCategory> captor;

    List<ProductCategory> tempList;

    @BeforeEach
    public void initEach() {
        ProductCategory cd = new ProductCategory();
        cd.setId(1L);
        cd.setCategoryName("CD");

        ProductCategory lp = new ProductCategory();
        lp.setId(2L);
        lp.setCategoryName("LP");

        tempList = new ArrayList<>();
        tempList.add(cd);
        tempList.add(lp);
    }

    @Test
    @DisplayName("Should pass if returns exact same number of categories as contained in tempList")
    void getProductCategories() {

        when(repository.findAll()).thenReturn(tempList);

        List<ProductCategory> categories = service.getProductCategories();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(tempList.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find category by given id")
    void getProductCategoryById() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(tempList.get(0)));

        ProductCategory returnedCategory = service.getProductCategoryById(anyLong());

        assertThat(returnedCategory.getCategoryName()).isEqualTo(tempList.get(0).getCategoryName());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw NotFoundException if category was not found")
    void categoryNotFoundById() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            ProductCategory returnedCategory = service.getProductCategoryById(anyLong());
        }).isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Category doesn't exist!");

        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should save category")
    void saveProductCategory() {

        when(repository.save(any(ProductCategory.class))).thenReturn(tempList.get(1));

        ProductCategory savedCategory = service.saveProductCategory(tempList.get(1));

        assertThat(savedCategory).isNotNull();
        verify(repository, times(1)).save(captor.capture());

        assertThat(captor.getValue().getCategoryName()).isEqualTo(tempList.get(1).getCategoryName());
    }

    @Test
    @DisplayName("Should update category")
    void updateProductCategory() {
        when(repository.save(any(ProductCategory.class))).thenReturn(tempList.get(1));

        ProductCategory savedCategory = service.saveProductCategory(tempList.get(1));
        savedCategory.setCategoryName("DVD");

        when(repository.findById(savedCategory.getId())).thenReturn(Optional.of(savedCategory));
        when(repository.save(any(ProductCategory.class))).thenReturn(savedCategory);

        ProductCategory updatedCategory = service.updateProductCategory(savedCategory);

        assertThat(savedCategory.getId()).isEqualTo(updatedCategory.getId());
        assertThat(savedCategory.getCategoryName()).isEqualTo(updatedCategory.getCategoryName());

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(2)).save(any(ProductCategory.class));
    }

    @Test
    @DisplayName("Should delete category by given object")
    void deleteProductCategory() {
        when(repository.findById(tempList.get(0).getId())).thenReturn(Optional.of(tempList.get(0)));

        service.deleteProductCategory(tempList.get(0));
        verify(repository, times(1)).delete(any(ProductCategory.class));
    }

    @Test
    @DisplayName("Should delete category by given id")
    void deleteProductCategoryById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(tempList.get(0)));

        service.deleteProductCategoryById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }
}