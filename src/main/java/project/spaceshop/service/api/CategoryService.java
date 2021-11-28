package project.spaceshop.service.api;

import org.jetbrains.annotations.NotNull;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Category;

import java.util.List;

/**
 * Interface provides API we can use to manipulate categories.
 */
public interface CategoryService {
    /**
     * Method looks for category by id.
     *
     * @param id param id of required category.
     * @return found category.
     */
    Category findCategoryById(int id);

    /**
     * Method adds category to database.
     *
     * @param categoryDto category dto object which we receive from variable data.
     */
    void saveCategory(CategoryDto categoryDto);

    /**
     * Method removes category from database by id.
     *
     * @param idCategory param id or category that must be removed.
     * @return true if category successfully removed or false if not.
     */
    boolean deleteCategoryById(int idCategory);

    /**
     * Method looks for all existing categories.
     *
     * @return all found categories in database.
     */
    List<Category> findAll();
}

