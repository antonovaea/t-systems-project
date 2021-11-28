package project.spaceshop.service.api;

import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;

import java.util.List;

/**
 * Interface provides API we can use to manipulate products in basket.
 */
public interface BasketProductService {

    /**
     * Method adds product to the shopping cart.
     *
     * @param basketProductDto product that must be added.
     * @param basket           all products in the basket.
     * @return true if product successfully added or false if not.
     */
    boolean addToBasket(BasketProductDto basketProductDto, List<BasketProductDto> basket);

    /**
     * Method removes product from shopping cart.
     *
     * @param id     id of the product which must be removed.
     * @param basket all products in basket.
     * @return true if product successfully removed or false if not.
     */
    boolean deleteFromBasketById(int id, List<BasketProductDto> basket);

    /**
     * Method counts amount of current product in shopping cart by id.
     *
     * @param id     id of the product which amount must be counted.
     * @param basket all products in basket.
     * @return amount of product in Integer format.
     */
    Integer countProductsInBasketById(int id, List<BasketProductDto> basket);

    /**
     * Method calculates total price of all products in shopping cart.
     *
     * @param basket all products in basket.
     * @return total price of all products in basket in Integer format.
     */
    int totalPrice(List<BasketProductDto> basket);

    /**
     * Method counts amount of all products in shopping cart.
     *
     * @param basket all products in basket.
     * @return total amount of all products in basket in Integer format.
     */
    Integer countProductsInBasket(List<BasketProductDto> basket);

    /**
     * Method removes all products from shopping cart.
     *
     * @param basket all products in basket.
     * @return new empty basket in ArrayList format.
     */
    List<BasketProductDto> deleteFromBasket(List<BasketProductDto> basket);

    /**
     * Method creates basket product {@link BasketProductDto} from product {@link Product}.
     *
     * @param product product that must be converted to BasketProductDto.
     * @return converted BasketProductDto object
     */
    BasketProductDto createBasketProductFromProduct(Product product);

    /**
     * Method converts basket product {@link BasketProductDto} to product {@link Product}.
     *
     * @param basketProductDto basket product object that must be converted ro product.
     * @return converted product object.
     */
    Product convertBasketProductDtoToProduct(BasketProductDto basketProductDto);

}
