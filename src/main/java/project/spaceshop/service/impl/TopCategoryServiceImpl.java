package project.spaceshop.service.impl;

import org.springframework.stereotype.Service;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.repository.TopCategoryRepository;
import project.spaceshop.service.api.TopCategoryService;


@Service
public class TopCategoryServiceImpl implements TopCategoryService {

    private final TopCategoryRepository topCategoryRepository;

    public TopCategoryServiceImpl(TopCategoryRepository topCategoryRepository) {
        this.topCategoryRepository = topCategoryRepository;
    }

    @Override
    public TopCategory saveTopCategory(TopCategory topCategory){
        return topCategoryRepository.save(topCategory);
    }

    @Override
    public void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount){
        int existingAmount = topCategory.getAmountOfSoldProducts();
        topCategory.setAmountOfSoldProducts(existingAmount + newAmount);
        topCategoryRepository.save(topCategory);
    }
}
