package project.spaceshop.dto;

import java.io.Serializable;

/**
 *
 */
public class BasketProductDto implements Serializable {

    private Integer id;
    private String productName;
    private byte[] productImage;
    private Integer amount;
    private Integer price;

    public BasketProductDto() {

    }

    public BasketProductDto(Integer id, String productName, byte[] productImage, Integer amount, Integer price) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.amount = amount;
        this.price = price;
    }

    public BasketProductDto(Integer id, String productName, Integer amount, Integer price) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BasketProductDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
