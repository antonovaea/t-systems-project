package project.spaceshop.service.impl;

import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.User;
import project.spaceshop.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public Order initOrder(){
        User user = new User();
        Order order = new Order();
        order.setDateOrder(new Date());
        order.setOrderStatus("Shipped");
        order.setOrderPrice(100);
        order.setId(1);
        order.setPaymentStatus("Paid");
        order.setPaymentMethod("Card");
        order.setUser(user);
        return order;
    }

    @Test
    void findAllOrder() {
        List<Order> orderList = new ArrayList<>();
        Order order = initOrder();
        orderList.add(order);

        when(orderRepository.findAll()).thenReturn(orderList);
        orderService.findAllOrder();
        verify(orderRepository).findAll();
    }

    @Test
    void getIncomeByDatePeriod() {
        Order order = initOrder();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        Date date1 = new Date();
        Date date2 = new Date();
        when(orderRepository.findOrderByDateOrderBetween(date1, date2)).thenReturn(orderList);

        int income = orderService.getIncomeByDatePeriod(date1, date2);

        assertEquals(100, income);
    }

    @Test
    void changeOrderStatusById() {
        Order order = initOrder();
        when(orderRepository.getById(order.getId())).thenReturn(order);
        boolean result = orderService.changeOrderStatusById(order.getId(), "DELIVERED");

        assertTrue(result);
        assertEquals("Delivered", order.getOrderStatus());
    }

}