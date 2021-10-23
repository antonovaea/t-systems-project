package project.spaceshop.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUser(User user);

    List<Order> findOrderByDateOrderBetween(@NotNull Date dateFirst, @NotNull Date dateSecond);



}
