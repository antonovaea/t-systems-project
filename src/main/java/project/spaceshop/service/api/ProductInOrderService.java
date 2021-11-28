package project.spaceshop.service.api;

import project.spaceshop.entity.ProductInOrder;

import java.util.List;

/**
 * Interface provides API we can use to manipulate products in order.
 */
public interface ProductInOrderService {

    /**
     * Method adds products in order to database.
     *
     * @param productInOrder product that is bought.
     * @return true if product in order successfully saved to database or false if not.
     */
    boolean saveProductInOrder(ProductInOrder productInOrder);

    /**
     * Method looks for all products in current order.
     *
     * @param orderId param id of order which products we would like to find.
     * @return products found in order by order id.
     */
    List<ProductInOrder> findAllByOrderId(int orderId);
}
