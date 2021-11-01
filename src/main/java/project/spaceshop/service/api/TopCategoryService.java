package project.spaceshop.service.api;

import project.spaceshop.entity.TopCategory;

public interface TopCategoryService {

    TopCategory saveTopCategory(TopCategory topCategory);

    void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount);
}
