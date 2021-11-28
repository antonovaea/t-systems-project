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

    private static final Logger log = LoggerFactory.getLogger(BasketProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public BasketProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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

    @Override
    public Integer countProductsInBasketById(int id, List<BasketProductDto> basket) {
        for (BasketProductDto product : basket) {
            if (product.getId() == id) {
                return product.getAmount();
            }
        }
        return 0;
    }

    @Override
    public int totalPrice(List<BasketProductDto> basket) {
        int totalPrice = 0;
        for (BasketProductDto product : basket) {
            totalPrice += product.getAmount() * product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public Integer countProductsInBasket(List<BasketProductDto> basket) {
        int count = 0;
        for (BasketProductDto product : basket) {
            count += product.getAmount();
        }
        return count;
    }

    @Override
    public List<BasketProductDto> deleteFromBasket(List<BasketProductDto> basket) {
        return new ArrayList<>();
//        try {
//            for (BasketProductDto product : basket) {
//                Product originalProduct = productRepository.getById(product.getId());
//                originalProduct.setAmountInStock(originalProduct.getAmountInStock() + product.getAmount());
//                productRepository.save(originalProduct);
//            }
//            log.info("all products removed from basket");
//            return new ArrayList<>();
//        } catch (Exception e) {
//            log.info("products have not removed from basket");
//            e.printStackTrace();
//            return basket;
//        }
    }

    @Override
    public BasketProductDto createBasketProductFromProduct(Product product) {
        return new BasketProductDto(product.getId(), product.getProductName(), product.getProductImage(), 1, product.getPrice());
    }

    @Override
    public Product convertBasketProductDtoToProduct(BasketProductDto basketProductDto) {
        if (basketProductDto == null) return null;
        return productRepository.getById(basketProductDto.getId());
    }
}
