package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
