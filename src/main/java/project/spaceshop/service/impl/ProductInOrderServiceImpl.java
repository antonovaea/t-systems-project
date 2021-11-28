package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.entity.ProductInOrder;
import project.spaceshop.repository.ProductInOrderRepository;
import project.spaceshop.service.api.ProductInOrderService;

import java.util.List;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    /**
     * Injected by spring ProductInOrderRepository bean
     */
    private final ProductInOrderRepository productInOrderRepository;

    /**
     * Injected constructor.
     *
     * @param productInOrderRepository that must be injected.
     */
    @Autowired
    public ProductInOrderServiceImpl(ProductInOrderRepository productInOrderRepository) {
        this.productInOrderRepository = productInOrderRepository;
    }

    /**
     * Method adds products in order to database.
     *
     * @param productInOrder product that is bought.
     * @return true if product in order successfully saved to database or false if not.
     */
    @Override
    public boolean saveProductInOrder(ProductInOrder productInOrder) {
        productInOrderRepository.save(productInOrder);
        return true;
    }

    /**
     * Method looks for all products in current order.
     *
     * @param orderId param id of order which products we would like to find.
     * @return products found in order by order id.
     */
    @Override
    public List<ProductInOrder> findAllByOrderId(int orderId) {
        return productInOrderRepository.findAllByOrderId(orderId);
    }
}
