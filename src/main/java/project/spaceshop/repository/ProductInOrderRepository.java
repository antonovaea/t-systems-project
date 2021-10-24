package project.spaceshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.ProductInOrder;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {

    List<ProductInOrder> findAllByOrderId(int idOrder);


}
