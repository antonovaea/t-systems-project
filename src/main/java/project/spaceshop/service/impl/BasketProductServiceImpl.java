package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.api.BasketProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    private final ProductRepository productRepository;

    @Autowired
    public BasketProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean addToBasket(BasketProductDto basketProductDto, List<BasketProductDto> basket) {
        Product product = productRepository.getById(basketProductDto.getId());
        boolean isFoundInBasket = false;
        for (BasketProductDto basketProduct : basket) {
            if (basketProduct.getId() == basketProductDto.getId()) {
                if (product.getAmountInStock() <= basketProductDto.getAmount() - basketProduct.getAmount())
                    return false;
                basketProduct.setAmount(basketProductDto.getAmount());
                basketProduct.setPrice(basketProductDto.getPrice());
                product.setAmountInStock(product.getAmountInStock() - basketProductDto.getAmount() + basketProduct.getAmount());
                isFoundInBasket = true;
                break;
            }
        }

        if (!isFoundInBasket)
            if (product.getAmountInStock() >= basketProductDto.getAmount()) {
                basket.add(basketProductDto);
                product.setAmountInStock(product.getAmountInStock() - basketProductDto.getAmount());
            } else return false;
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteFromBasketById(int id, List<BasketProductDto> basket) {
        for (BasketProductDto product : basket) {
            if (product.getId() == id) {
                Product originalProduct = productRepository.getById(id);
                originalProduct.setAmountInStock(originalProduct.getAmountInStock() + product.getAmount());
                productRepository.save(originalProduct);
                basket.remove(product);
                break;
            }
        }
        return true;
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
        for (BasketProductDto product : basket) {
            Product originalProduct = productRepository.getById(product.getId());
            originalProduct.setAmountInStock(originalProduct.getAmountInStock() + product.getAmount());
            productRepository.save(originalProduct);
        }

        return new ArrayList<>();
    }

    @Override
    public BasketProductDto createBasketProductFromProduct(Product product) {
        return new BasketProductDto(product.getId(), product.getProductName(), 1, product.getPrice());
    }
}
