package project.spaceshop.service.impl;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.dto.converter.CategoryConverter;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.TopCategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final TopCategoryService topCategoryService;

    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, TopCategoryService topCategoryService, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.topCategoryService = topCategoryService;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public @NotNull Category findCategoryById(int id) {
        return categoryRepository.getById(id);
    }


    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        TopCategory topCategory = new TopCategory();
        try {
            category.setCategoryName(categoryDto.getCategoryName());
            categoryRepository.save(category);
            topCategory.setCategory(category);
            topCategory.setAmountOfSoldProducts(0);
            topCategory.setTopCategoryName(category.getCategoryName());
            topCategoryService.saveTopCategory(topCategory);
            log.info("category saved " + category.getCategoryName());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("category has not saved " + category.getCategoryName());
        }
    }

    @Override
    public void deleteCategoryById(int idCategory) {
        try {
            categoryRepository.deleteById(idCategory);
            log.info("category with id " + idCategory + " removed");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("category with id " + idCategory + " has not removed");
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
