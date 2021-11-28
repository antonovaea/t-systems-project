package project.spaceshop.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.Category;
import project.spaceshop.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    public Category initCategory() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("1");
        return category;
    }

    public Category initCategory2() {
        Category category2 = new Category();
        category2.setId(2);
        category2.setCategoryName("2");
        return category2;
    }

    public List<Category> initCategoryList() {
        Category category = initCategory();
        Category category2 = initCategory2();
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category2);
        return categories;
    }

    @Test
    void findCategoryById() {
        Category category = initCategory();
        when(categoryRepository.getById(category.getId())).thenReturn(category);
        Category selectedCategory = categoryService.findCategoryById(1);
        assertEquals(category, selectedCategory);
    }

    @Test
    void deleteCategoryById() {
        Category category = initCategory();
        boolean result = categoryService.deleteCategoryById(category.getId());
        assertTrue(result);
    }


    @Test
    void findAll() {
        List<Category> categories = initCategoryList();
        when(categoryRepository.findAll()).thenReturn(categories);
        categoryService.findAll();
        verify(categoryRepository).findAll();
    }
}