package project.spaceshop.entity;


import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "product_in_order", schema = "planetshop")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_in_order")
    private int id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order")
    private Order order;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private Product product;

    @NotNull
    @Column(name = "amount_in_order")
    private Integer amountInOrder;

    public ProductInOrder(){

    }

    public ProductInOrder(int id, @NotNull Order order, @NotNull Product product, @NotNull Integer amountInOrder) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.amountInOrder = amountInOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public Order getOrder() {
        return order;
    }

    public void setOrder(@NotNull Order order) {
        this.order = order;
    }

    @NotNull
    public Product getProduct() {
        return product;
    }

    public void setProduct(@NotNull Product product) {
        this.product = product;
    }

    @NotNull
    public Integer getAmountInOrder() {
        return amountInOrder;
    }

    public void setAmountInOrder(@NotNull Integer amountInOrder) {
        this.amountInOrder = amountInOrder;
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", amountInOrder=" + amountInOrder +
                '}';
    }
}
