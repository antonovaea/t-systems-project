package project.spaceshop.service.api;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import project.spaceshop.dto.BasketProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.Product;
import project.spaceshop.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Interface provides API we can use to manipulate orders.
 */
public interface OrderService {
    /**
     * Method adds order to database.
     *
     * @param idAddress   param id of shipping address.
     * @param paymentType type of selected payment method, may be card or cash.
     * @param basket      all ordered products.
     * @return saved order.
     */
    Order saveOrder(int idAddress, String paymentType, List<BasketProductDto> basket);

    /**
     * Method divides list of existing orders on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing orders on one page.
     * @return pages with existing orders.
     */
    Page<Order> findPaginated(int pageNo, int pageSize);

    /**
     * Method divides list of existing orders of current user on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing orders on one page.
     * @return pages with existing and previous orders of current user.
     */
    Page<Order> findPaginatedOrderByUser(int pageNo, int pageSize);

    /**
     * Method looks for all existing orders.
     *
     * @return all orders found in database.
     */
    List<Order> findAllOrder();

    /**
     * Method calculates total income for the period of time between two selected dates.
     *
     * @param dateFirst  start period date.
     * @param dateSecond end period date.
     * @return total income obtained in the selected period of time in Integer form.
     */
    int getIncomeByDatePeriod(Date dateFirst, Date dateSecond);

    /**
     * Method changes order status in database by id.
     *
     * @param idOrder     param id of order which status must be changed.
     * @param orderStatus new order status that we want to set up.
     * @return true if status successfully changed or false if not.
     */
    boolean changeOrderStatusById(int idOrder, String orderStatus);

    /**
     * Method sends email message with order info to user email address after placing order.
     *
     * @param order     placed order.
     * @param user      user which placed new order.
     * @param idAddress param id or user shipping address.
     */
    void sendEmailMessage(Order order, User user, int idAddress);
}
