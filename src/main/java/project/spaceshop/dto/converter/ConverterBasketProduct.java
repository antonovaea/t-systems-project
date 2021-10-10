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

    public Product fromBasketProductDtoToProduct(BasketProductDto basketProductDto){
        Product product = new Product();
        product.setId(basketProductDto.getId());
        product.setProductName(basketProductDto.getProductName());
        product.setAmountInStock(basketProductDto.getAmount());
        product.setPrice(basketProductDto.getPrice());
        product.setProductImage(basketProductDto.getProductImage());
        return product;
    }
}
