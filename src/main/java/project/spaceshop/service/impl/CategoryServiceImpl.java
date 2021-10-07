package project.spaceshop.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.service.api.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
    }

}
