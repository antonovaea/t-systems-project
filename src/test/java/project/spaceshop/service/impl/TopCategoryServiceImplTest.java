package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.dto.converter.TopCategoryConverter;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.repository.TopCategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TopCategoryServiceImplTest {

    @Mock
    private TopCategoryRepository topCategoryRepository;

    @Mock
    private TopCategoryConverter topCategoryConverter;

    @InjectMocks
    private TopCategoryServiceImpl topCategoryService;

    @Before
    public Category initCategory() {
        Category category = new Category();
        category.setId(1);
        category.setCategoryName("new");
        return category;
    }

    @Before
    public TopCategory initTopCategory() {
        Category category = initCategory();
        TopCategory topCategory = new TopCategory();
        topCategory.setId(1);
        topCategory.setTopCategoryName("new");
        topCategory.setCategory(category);
        topCategory.setAmountOfSoldProducts(10);
        return topCategory;
    }

    @Test
    public void saveTopCategory() {
        TopCategory topCategory = initTopCategory();
        boolean result = topCategoryService.saveTopCategory(topCategory);
        assertTrue(result);
    }

    @Test
    public void changeAmountOfSoldProducts() {
        TopCategory topCategory = initTopCategory();
        int newAmount = 10;
        topCategoryService.changeAmountOfSoldProducts(topCategory, newAmount);
        assertEquals(20, topCategory.getAmountOfSoldProducts());

    }

    @Test
    public void findAllTop() {
        TopCategoryDto topCategoryDto = new TopCategoryDto(1, "new", 10);
        List<TopCategoryDto> topList = new ArrayList<>();
        topList.add(topCategoryDto);

        TopCategory topCategory = initTopCategory();
        List<TopCategory> topCategories = new ArrayList<>();
        topCategories.add(topCategory);

        when(topCategoryRepository.findAll()).thenReturn(topCategories);
        when(topCategoryConverter.fromTopCategoryToTopCategoryDto(topCategory)).thenReturn(topCategoryDto);
        List<TopCategoryDto> selectedList = topCategoryService.findAllTop();
        assertEquals(selectedList, topList);
    }
}