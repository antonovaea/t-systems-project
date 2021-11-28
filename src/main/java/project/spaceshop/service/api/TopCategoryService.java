package project.spaceshop.service.api;

import org.springframework.data.domain.Sort;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.entity.TopCategory;

import java.util.List;

/**
 * Interface provides API we can use to manipulate top categories.
 */
public interface TopCategoryService {

    /**
     * Method adds top category to database.
     *
     * @param topCategory top category object that must be saved.
     * @return true if top category successfully saved to database or false if not.
     */
    boolean saveTopCategory(TopCategory topCategory);

    /**
     * Method changes amount of sold products in current top category.
     *
     * @param topCategory top category object which amount of sold products must be changed.
     * @param newAmount   number of newly purchased products belonging to the category.
     */
    void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount);

    /**
     * Method looks for all existing top categories.
     *
     * @return list of TopCategoryDto objects.
     */
    List<TopCategoryDto> findAllTop();
}
