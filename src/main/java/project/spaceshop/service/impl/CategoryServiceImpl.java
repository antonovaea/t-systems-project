package project.spaceshop.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.TopCategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final TopCategoryService topCategoryService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, TopCategoryService topCategoryService) {
        this.categoryRepository = categoryRepository;
        this.topCategoryService = topCategoryService;
    }

    @Override
    public @NotNull Category findCategoryById(int id) {
        return categoryRepository.getById(id);
    }


    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepository.save(category);
        TopCategory topCategory = new TopCategory();
        topCategory.setCategory(category);
        topCategory.setAmountOfSoldProducts(0);
        topCategoryService.saveTopCategory(topCategory);
    }

    @Override
    public void deleteCategoryById(int idCategory) {
        categoryRepository.deleteById(idCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
