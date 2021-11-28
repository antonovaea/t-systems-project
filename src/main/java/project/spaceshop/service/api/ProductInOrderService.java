package project.spaceshop.service.api;

import project.spaceshop.entity.ProductInOrder;

import java.util.List;

public interface ProductInOrderService {

    boolean saveProductInOrder(ProductInOrder productInOrder);

    List<ProductInOrder> findAllByOrderId(int orderId);
}
