package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders", schema = "planetshop")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    @Column(name = "address")
    private String userAddress;

    @NotNull
    @Column(name = "order_status")
    private String orderStatus;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date_order")
    private Date dateOrder;

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;

    @NotNull
    @Column(name = "order_price")
    private Integer orderPrice;

    @NotNull
    @Column(name = "payment_status")
    private String paymentStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.MERGE)
    private List<ProductInOrder> products = new ArrayList<>();

    public Order() {
    }

    public Order(int id, @NotNull User user, @NotNull String userAddress, @NotNull String orderStatus, @NotNull Date dateOrder, @NotNull String paymentMethod, @NotNull Integer orderPrice, @NotNull String paymentStatus, List<ProductInOrder> products) {
        this.id = id;
        this.user = user;
        this.userAddress = userAddress;
        this.orderStatus = orderStatus;
        this.dateOrder = dateOrder;
        this.paymentMethod = paymentMethod;
        this.orderPrice = orderPrice;
        this.paymentStatus = paymentStatus;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(@NotNull User client) {
        this.user = user;
    }

    @NotNull
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@NotNull String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @NotNull
    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(@NotNull Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    @NotNull
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotNull String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @NotNull
    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(@NotNull Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    @NotNull
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(@NotNull String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInOrder> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderStatus='" + orderStatus + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", dateOrder=" + dateOrder +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderPrice=" + orderPrice +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", products=" + products +
                '}';
    }
}

