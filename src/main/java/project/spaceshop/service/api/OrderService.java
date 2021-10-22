package project.spaceshop.service.api;

import project.spaceshop.dto.BasketProductDto;
import project.spaceshop.entity.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket);

    List<Order> findOrderByUser();

    List<Order> findAllOrder();

    boolean changeOrderStatusById(int idOrder, String orderStatus);
}
