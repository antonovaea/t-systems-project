package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.repository.UserAddressRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Before
    public Category initCategory(){
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("new");
        return category;
    }

    @Test
    void findCategoryById() {
        Category category = initCategory();
        when(categoryRepository.getById(category.getId())).thenReturn(category);
        Category selectedCategory = categoryService.findCategoryById(1);
        assertEquals(category, selectedCategory);
    }

    @Test
    void saveCategory() {
    }

    @Test
    void deleteCategoryById() {
    }

    @Test
    void findAll() {
    }
}