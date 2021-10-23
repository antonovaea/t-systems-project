package project.spaceshop.service.api;

import org.jetbrains.annotations.NotNull;
import project.spaceshop.dto.BasketProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket);

    List<Order> findOrderByUser();

    List<Order> findAllOrder();

    List<Order> findByDatePeriod(Date dateFirst,Date dateSecond);

    boolean changeOrderStatusById(int idOrder, String orderStatus);
}
