package project.spaceshop.dto;

import java.io.Serializable;

public class ProductSendDto implements Serializable {

    private Integer id;

    private String productName;

    private Integer price;

    private Integer salesAmount;

    private String image;

    public ProductSendDto(){

    }

    public ProductSendDto(Integer id, String productName, Integer price, String image) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.image = image;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductSendDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", salesAmount=" + salesAmount +
                ", image='" + image + '\'' +
                '}';
    }
}
