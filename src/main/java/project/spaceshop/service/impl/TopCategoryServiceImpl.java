package project.spaceshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.dto.converter.TopCategoryConverter;
import project.spaceshop.entity.TopCategory;
import project.spaceshop.repository.TopCategoryRepository;
import project.spaceshop.service.api.TopCategoryService;

import java.util.ArrayList;
import java.util.List;


@Service
public class TopCategoryServiceImpl implements TopCategoryService {

    private static final Logger log = LoggerFactory.getLogger(TopCategoryServiceImpl.class);

    private final TopCategoryRepository topCategoryRepository;

    private final TopCategoryConverter topCategoryConverter;

    public TopCategoryServiceImpl(TopCategoryRepository topCategoryRepository, TopCategoryConverter topCategoryConverter) {
        this.topCategoryRepository = topCategoryRepository;
        this.topCategoryConverter = topCategoryConverter;
    }

    @Override
    public TopCategory saveTopCategory(TopCategory topCategory){
        log.info("top category saved " + topCategory.getTopCategoryName());
        return topCategoryRepository.save(topCategory);
    }

    @Override
    public void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount){
        try {
            int existingAmount = topCategory.getAmountOfSoldProducts();
            topCategory.setAmountOfSoldProducts(existingAmount + newAmount);
            topCategoryRepository.save(topCategory);
            log.info("amount of sold products of top category " + topCategory.getTopCategoryName() + " changed");
        } catch (Exception e) {
            log.info("amount of sold products of top category " + topCategory.getTopCategoryName() + " has not changed");
            e.printStackTrace();
        }
    }

    @Override
    public List<TopCategoryDto> findAllTop() {
        List<TopCategory> list = topCategoryRepository.findAll();
        List<TopCategoryDto> topList = new ArrayList<>();
        for (TopCategory category : list){
            if(category.getAmountOfSoldProducts() != 0){
                topList.add(topCategoryConverter.fromTopCategoryToTopCategoryDto(category));
            }
        }
        return topList;
    }

}
