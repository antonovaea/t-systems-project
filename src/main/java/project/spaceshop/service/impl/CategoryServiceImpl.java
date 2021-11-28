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

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    /**
     * Injected by spring CategoryRepository bean
     */
    private final CategoryRepository categoryRepository;

    /**
     * Injected by spring TopCategoryService bean
     */
    private final TopCategoryService topCategoryService;

    /**
     * Injected by spring CategoryConverter bean
     */
    private final CategoryConverter categoryConverter;

    /**
     * Injected constructor.
     *
     * @param categoryRepository that must be injected.
     * @param topCategoryService that must be injected.
     * @param categoryConverter  that must be injected.
     */
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, TopCategoryService topCategoryService, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.topCategoryService = topCategoryService;
        this.categoryConverter = categoryConverter;
    }

    /**
     * Method looks for category by id.
     *
     * @param id param id of required category.
     * @return found category.
     */
    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.getById(id);
    }

    /**
     * Method adds category to database.
     *
     * @param categoryDto category dto object which we receive from variable data.
     */
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

    /**
     * Method removes category from database by id.
     *
     * @param idCategory param id or category that must be removed.
     * @return true if category successfully removed or false if not.
     */
    @Override
    public boolean deleteCategoryById(int idCategory) {
        try {
            categoryRepository.deleteById(idCategory);
            log.info("category with id " + idCategory + " removed");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("category with id " + idCategory + " has not removed");
            return false;
        }
    }

    /**
     * Method looks for all existing categories.
     *
     * @return all found categories in database.
     */
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
