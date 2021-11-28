package project.spaceshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;
import project.spaceshop.mq.RabbitMqSender;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.api.BasketProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(BasketProductServiceImpl.class);

    /**
     * Injected by Spring ProductRepository bean.
     */
    private final ProductRepository productRepository;

    /**
     * Injected constructor.
     *
     * @param productRepository that must be injected.
     */
    @Autowired
    public BasketProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Method adds product to the shopping cart.
     *
     * @param basketProductDto product that must be added.
     * @param basket           all products in the basket.
     * @return true if product successfully added or false if not.
     */
    @Override
    public boolean addToBasket(BasketProductDto basketProductDto, List<BasketProductDto> basket) {
        try {
            Product product = productRepository.getById(basketProductDto.getId());
            boolean isFoundInBasket = false;
            for (BasketProductDto basketProduct : basket) {
                if (basketProduct.getId() == basketProductDto.getId()) {
                    if (product.getAmountInStock() <= basketProductDto.getAmount() - basketProduct.getAmount())
                        return false;
                    basketProduct.setAmount(basketProductDto.getAmount());
                    basketProduct.setPrice(basketProductDto.getPrice());
                    isFoundInBasket = true;
                    break;
                }
            }

            if (!isFoundInBasket)
                if (product.getAmountInStock() >= basketProductDto.getAmount()) {
                    basket.add(basketProductDto);
                } else return false;
            log.info("product " + basketProductDto.getProductName() + " added to the basket");
            return true;
        } catch (Exception e) {
            log.info("product" + basketProductDto.getProductName() + " has not added to the basket");
            return false;
        }
    }

    /**
     * Method removes product from shopping cart.
     *
     * @param id     id of the product which must be removed.
     * @param basket all products in basket.
     * @return true if product successfully removed or false if not.
     */
    @Override
    public boolean deleteFromBasketById(int id, List<BasketProductDto> basket) {
        try {
            for (BasketProductDto product : basket) {
                if (product.getId() == id) {
                    basket.remove(product);
                    log.info("product " + product.getProductName() + " removed from basket");
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("product has not removed");
            return false;
        }
    }

    /**
     * Method counts amount of current product in shopping cart by id.
     *
     * @param id     id of the product which amount must be counted.
     * @param basket all products in basket.
     * @return amount of product in Integer format.
     */
    @Override
    public Integer countProductsInBasketById(int id, List<BasketProductDto> basket) {
        for (BasketProductDto product : basket) {
            if (product.getId() == id) {
                return product.getAmount();
            }
        }
        return 0;
    }

    /**
     * Method calculates total price of all products in shopping cart.
     *
     * @param basket all products in basket.
     * @return total price of all products in basket in Integer format.
     */
    @Override
    public int totalPrice(List<BasketProductDto> basket) {
        int totalPrice = 0;
        for (BasketProductDto product : basket) {
            totalPrice += product.getAmount() * product.getPrice();
        }
        return totalPrice;
    }

    /**
     * Method counts amount of all products in shopping cart.
     *
     * @param basket all products in basket.
     * @return total amount of all products in basket in Integer format.
     */
    @Override
    public Integer countProductsInBasket(List<BasketProductDto> basket) {
        int count = 0;
        for (BasketProductDto product : basket) {
            count += product.getAmount();
        }
        return count;
    }

    /**
     * Method removes all products from shopping cart.
     *
     * @param basket all products in basket.
     * @return new empty basket in ArrayList format.
     */
    @Override
    public List<BasketProductDto> deleteFromBasket(List<BasketProductDto> basket) {
        return new ArrayList<>();
    }

    /**
     * Method creates basket product {@link BasketProductDto} from product {@link Product}.
     *
     * @param product product that must be converted to BasketProductDto.
     * @return converted BasketProductDto object
     */
    @Override
    public BasketProductDto createBasketProductFromProduct(Product product) {
        return new BasketProductDto(product.getId(), product.getProductName(), product.getProductImage(), 1, product.getPrice());
    }

    /**
     * Method converts basket product {@link BasketProductDto} to product {@link Product}.
     *
     * @param basketProductDto basket product object that must be converted ro product.
     * @return converted product object.
     */
    @Override
    public Product convertBasketProductDtoToProduct(BasketProductDto basketProductDto) {
        if (basketProductDto == null) return null;
        return productRepository.getById(basketProductDto.getId());
    }
}
