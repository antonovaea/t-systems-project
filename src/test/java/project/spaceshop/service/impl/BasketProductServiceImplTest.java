package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BasketProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private BasketProductServiceImpl basketProductService;

    @Before
    public Product initProduct(){
        Product product = new Product();
        product.setProductName("Product");
        product.setId(1);
        product.setPrice(100);
        product.setAmountInStock(1);
        return product;
    }

    @Before
    public BasketProductDto initBasketProductDto(){
        BasketProductDto basketProductDto = new BasketProductDto();
        basketProductDto.setProductName("Product");
        basketProductDto.setPrice(100);
        basketProductDto.setAmount(1);
        basketProductDto.setId(1);
        return basketProductDto;
    }


    @org.junit.jupiter.api.Test
    public void addToBasket() {
        Product product = initProduct();
        BasketProductDto basketProductDto = initBasketProductDto();
        List<BasketProductDto> basket = new ArrayList<>();
        basket.add(basketProductDto);
        when(productRepository.getById(basketProductDto.getId())).thenReturn(product);
        basketProductService.addToBasket(basketProductDto, basket);
        assertEquals(1, basket.size());

    }

    @Test
    public void deleteFromBasketById() {
        List<BasketProductDto> basket = new ArrayList<>();
        BasketProductDto basketProductDto = initBasketProductDto();
        basket.add(basketProductDto);

        boolean result = basketProductService.deleteFromBasketById(basketProductDto.getId(), basket);
        assertTrue(result);
        assertEquals(0, basket.size());
    }

    @Test
    public void countProductsInBasketById() {
        List<BasketProductDto> basket = new ArrayList<>();
        BasketProductDto basketProductDto = initBasketProductDto();
        basket.add(basketProductDto);

        int result = basketProductService.countProductsInBasketById(basketProductDto.getId(), basket);
        assertEquals(1, result);
    }

    @Test
    public void totalPrice() {
        List<BasketProductDto> basket = new ArrayList<>();
        BasketProductDto basketProductDto = initBasketProductDto();
        BasketProductDto basketProductDto1 = initBasketProductDto();
        basket.add(basketProductDto);
        basket.add(basketProductDto1);

        int result = basketProductService.totalPrice(basket);

        assertEquals(200, result);
    }

    @Test
    public void countProductsInBasket() {
        List<BasketProductDto> basket = new ArrayList<>();
        BasketProductDto basketProductDto = initBasketProductDto();
        BasketProductDto basketProductDto1 = initBasketProductDto();
        basket.add(basketProductDto);
        basket.add(basketProductDto1);

        int result = basketProductService.countProductsInBasket(basket);

        assertEquals(2, result);

    }

    @Test
    public void deleteFromBasket() {
        List<BasketProductDto> basket = new ArrayList<>();
        BasketProductDto basketProductDto = initBasketProductDto();
        BasketProductDto basketProductDto1 = initBasketProductDto();
        basket.add(basketProductDto);
        basket.add(basketProductDto1);

        List<BasketProductDto> afterDeleting = basketProductService.deleteFromBasket(basket);

        assertEquals(0, afterDeleting.size());
    }

    @Test
    public void createBasketProductFromProduct() {
        Product product = initProduct();
        BasketProductDto basketProductDto = initBasketProductDto();

        BasketProductDto dtoFromProduct = basketProductService.createBasketProductFromProduct(product);

        assertEquals(basketProductDto.toString(), dtoFromProduct.toString());
    }

    @Test
    public void convertBasketProductDtoToProduct() {
        Product product = initProduct();
        BasketProductDto basketProductDto = initBasketProductDto();
        when(productRepository.getById(product.getId())).thenReturn(product);

        Product convertedProduct = basketProductService.convertBasketProductDtoToProduct(basketProductDto);

        assertEquals(product, convertedProduct);

    }
}