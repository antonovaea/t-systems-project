package project.spaceshop.service.api;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import project.spaceshop.dto.BasketProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.Product;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket);

    Page<Order> findPaginated(int pageNo, int pageSize);

    Page<Order> findPaginatedOrderByUser(int pageNo, int pageSize);

    List<Order> findAllOrder();

    List<Order> findByDatePeriod(Date dateFirst,Date dateSecond);

    boolean changeOrderStatusById(int idOrder, String orderStatus);
}
