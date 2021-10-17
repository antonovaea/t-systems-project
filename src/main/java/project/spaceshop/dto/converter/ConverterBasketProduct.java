package project.spaceshop.dto.converter;

import org.springframework.stereotype.Component;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.entity.Product;

@Component
public class ConverterBasketProduct {
    public BasketProductDto fromProductToBasketProductDto(Product product){
        BasketProductDto basketProductDto = new BasketProductDto();
        basketProductDto.setId(product.getId());
        basketProductDto.setProductName(product.getProductName());
        basketProductDto.setAmount(product.getAmountInStock());
        basketProductDto.setPrice(product.getPrice());
        basketProductDto.setProductImage(product.getProductImage());
        return basketProductDto;
    }
}
