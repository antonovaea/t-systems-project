package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.ProductInOrder;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.service.api.ProductInOrderService;

import java.util.List;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    private final ProductInOrderRepository productInOrderRepository;

    @Autowired
    public ProductInOrderServiceImpl(ProductInOrderRepository productInOrderRepository) {
        this.productInOrderRepository = productInOrderRepository;
    }

    @Override
    public ProductInOrder saveProductInOrder(ProductInOrder productInOrder) {
        return productInOrderRepository.save(productInOrder);
    }

    @Override
    public List<ProductInOrder> findAllByOrderId(int orderId) {
        return productInOrderRepository.findAllByOrderId(orderId);
    }
}
