package project.spaceshop.service.api;

import org.jetbrains.annotations.NotNull;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;

public interface CategoryService {

    @NotNull Category findCategoryById(int id);

    void saveCategory(CategoryDto categoryDto);

    void deleteCategoryById(int idCategory);
}

