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

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TopCategoryServiceImpl.class);

    /**
     * Injected by spring TopCategoryRepository bean
     */
    private final TopCategoryRepository topCategoryRepository;

    /**
     * Injected by spring TopCategoryConverter bean
     */
    private final TopCategoryConverter topCategoryConverter;

    /**
     * Injected constructor.
     *
     * @param topCategoryRepository that must be injected.
     * @param topCategoryConverter  that must be injected.
     */
    public TopCategoryServiceImpl(TopCategoryRepository topCategoryRepository, TopCategoryConverter topCategoryConverter) {
        this.topCategoryRepository = topCategoryRepository;
        this.topCategoryConverter = topCategoryConverter;
    }

    /**
     * Method adds top category to database.
     *
     * @param topCategory top category object that must be saved.
     * @return true if top category successfully saved to database or false if not.
     */
    @Override
    public boolean saveTopCategory(TopCategory topCategory) {
        log.info("top category saved " + topCategory.getTopCategoryName());
        topCategoryRepository.save(topCategory);
        return true;
    }

    /**
     * Method changes amount of sold products in current top category.
     *
     * @param topCategory top category object which amount of sold products must be changed.
     * @param newAmount   number of newly purchased products belonging to the category.
     */
    @Override
    public void changeAmountOfSoldProducts(TopCategory topCategory, int newAmount) {
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

    /**
     * Method looks for all existing top categories.
     *
     * @return list of TopCategoryDto objects.
     */
    @Override
    public List<TopCategoryDto> findAllTop() {
        List<TopCategory> list = topCategoryRepository.findAll();
        List<TopCategoryDto> topList = new ArrayList<>();
        for (TopCategory category : list) {
            if (category.getAmountOfSoldProducts() != 0) {
                topList.add(topCategoryConverter.fromTopCategoryToTopCategoryDto(category));
            }
        }
        return topList;
    }

}
