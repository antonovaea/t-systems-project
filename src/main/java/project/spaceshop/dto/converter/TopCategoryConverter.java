package project.spaceshop.dto.converter;

import org.springframework.stereotype.Component;
import project.spaceshop.dto.TopCategoryDto;
import project.spaceshop.entity.TopCategory;

@Component
public class TopCategoryConverter {
    public TopCategoryDto fromTopCategoryToTopCategoryDto(TopCategory topCategory){
        TopCategoryDto topCategoryDto = new TopCategoryDto();
        topCategoryDto.setId(topCategory.getId());
        topCategoryDto.setTopCategoryName(topCategory.getTopCategoryName());
        topCategoryDto.setAmountOfSoldProducts(topCategory.getAmountOfSoldProducts());
        return topCategoryDto;
    }
}
