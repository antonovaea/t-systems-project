package project.spaceshop.dto.converter;

import org.springframework.stereotype.Component;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;

@Component
public class CategoryConverter {
    public Category fromCategoryDtoToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(category.getId());
        category.setCategoryName(category.getCategoryName());
        return category;
    }

    public CategoryDto fromCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        return categoryDto;
    }
}
