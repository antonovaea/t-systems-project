package project.spaceshop.service.impl;

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

    private final TopCategoryRepository topCategoryRepository;

    private final TopCategoryConverter topCategoryConverter;

    public TopCategoryServiceImpl(TopCategoryRepository topCategoryRepository, TopCategoryConverter topCategoryConverter) {
        this.topCategoryRepository = topCategoryRepository;
        this.topCategoryConverter = topCategoryConverter;
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

//    @Override
//    public List<TopCategory> findAllSortedByAmountOfSoldProducts(List<Integer> amountOfSoldProducts, Sort sort) {
//        List<TopCategory> list = topCategoryRepository.findAll();
//        List<TopCategory> topList = new ArrayList<>();
//        for (TopCategory category : list){
//            if(category.getAmountOfSoldProducts() != 0){
//                topList.add(category);
//            }
//        }
//
//        Sort sort1 = new Sort(Sort.Direction.DESC, List<Integer> amountOfSoldProducts);
//    }


}
