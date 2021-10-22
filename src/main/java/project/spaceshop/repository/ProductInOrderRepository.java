package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.ProductInOrder;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {
}
