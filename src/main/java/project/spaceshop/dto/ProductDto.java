package project.spaceshop.dto;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Arrays;

public class ProductDto implements Serializable {

    private int id;

    private String productName;

    private Integer idCategory;

    private Integer price;

    private String productDescription;

    private Integer amountInStock;

    @Lob
    private byte[] productImage;

    @Column(name = "inhabitants")
    private String inhabitants;

    public ProductDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        this.amountInStock = amountInStock;
    }

    public String getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(String inhabitants) {
        this.inhabitants = inhabitants;
    }

    public ProductDto(int id, String productName, Integer idCategory, Integer price, String productDescription, Integer amountInStock, byte[] productImage, String inhabitants) {
        this.id = id;
        this.productName = productName;
        this.idCategory = idCategory;
        this.price = price;
        this.productDescription = productDescription;
        this.amountInStock = amountInStock;
        this.productImage = productImage;
        this.inhabitants = inhabitants;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", idCategory=" + idCategory +
                ", price=" + price +
                ", productDescription='" + productDescription + '\'' +
                ", amountInStock=" + amountInStock +
                ", productImage=" + Arrays.toString(productImage) +
                ", inhabitants='" + inhabitants + '\'' +
                '}';
    }
}
