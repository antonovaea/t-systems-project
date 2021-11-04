package project.spaceshop.service.api;

import org.springframework.data.domain.Sort;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.entity.TopCategory;

import java.util.List;

public interface TopCategoryService {

    TopCategory saveTopCategory(TopCategory topCategory);

    void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount);

//    List<TopCategory> findAllSortedByAmountOfSoldProducts(List<Integer> amountOfSoldProducts, Sort sort);

    List<TopCategoryDto> findAllTop();
}
