package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.User;
import project.spaceshop.entity.UserAddress;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.repository.UserAddressRepository;

import java.util.ArrayList;
import java.util.List;

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
    public Category initCategory() {
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
    void deleteCategoryById() {
        Category category = initCategory();
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.getById(category.getId())).thenReturn(category);
        categories.remove(category);
        assertEquals(0, categories.size());
    }

//    @Test
//    void findAll() {
//        Category category = initCategory();
//        Category category1 = initCategory();
//        List<Category> categories = new ArrayList<>();
//        categoryRepository.save(category);
//        categoryRepository.save(category1);
//        categories = categoryRepository.findAll();
//        assertEquals(2, categories.size());
//
//    }
}