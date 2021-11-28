package project.spaceshop.service.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.Product;
import project.spaceshop.entity.ProductInOrder;
import project.spaceshop.repository.ProductInOrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


import static org.junit.Assert.*;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductInOrderServiceImplTest {

    @Mock
    private Order order;

    @Mock
    private Product product;

    @Mock
    private ProductInOrderRepository productInOrderRepository;

    @InjectMocks
    private ProductInOrderServiceImpl productInOrderService;

    ProductInOrderServiceImplTest() {
    }

    @Before
    public ProductInOrder initProductInOrder(){
        order.setId(2);
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        productInOrder.setOrder(order);
        productInOrder.setId(1);
        productInOrder.setAmountInOrder(1);
        return productInOrder;
    }

    @Test
    public void saveProductInOrder() {
        ProductInOrder productInOrder = initProductInOrder();
        boolean result = productInOrderService.saveProductInOrder(productInOrder);
        assertTrue(result);
    }

    @Test
    public void findAllByOrderId() {
        Order order = new Order();
        order.setId(2);
        ProductInOrder productInOrder = initProductInOrder();
        List<ProductInOrder> productInOrderList = new ArrayList<>();
        productInOrderList.add(productInOrder);
        when(productInOrderRepository.findAllByOrderId(order.getId())).thenReturn(productInOrderList);
        List<ProductInOrder> selectedList = productInOrderService.findAllByOrderId(order.getId());

        assertEquals(productInOrderList, selectedList);
    }
}