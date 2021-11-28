package project.spaceshop.service.api;

import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;

import java.util.List;

public interface BasketProductService {

    boolean addToBasket(BasketProductDto basketProductDto, List<BasketProductDto> basket);

    boolean deleteFromBasketById(int id, List<BasketProductDto> basket);

    Integer countProductsInBasketById(int id, List<BasketProductDto> basket);

    int totalPrice(List<BasketProductDto> basket);

    Integer countProductsInBasket(List<BasketProductDto> basket);

    List<BasketProductDto> deleteFromBasket(List<BasketProductDto> basket);

    BasketProductDto createBasketProductFromProduct(Product product);

    Product convertBasketProductDtoToProduct(BasketProductDto basketProductDto);

}
