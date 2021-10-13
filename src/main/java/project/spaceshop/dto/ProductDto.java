package project.spaceshop.dto;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Arrays;

public class ProductDto implements Serializable {

    private int id;

    @NotNull
    private String productName;

    @NotNull
    private Integer idCategory;

    @NotNull
    private Integer price;

    @NotNull
    private String productDescription;

    @NotNull
    private Integer amountInStock;

    @Lob
    private byte[] productImage;

    @NotNull
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

    @NotNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    @NotNull
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(@NotNull Integer idCategory) {
        this.idCategory = idCategory;
    }

    @NotNull
    public Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull Integer price) {
        this.price = price;
    }

    @NotNull
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(@NotNull String productDescription) {
        this.productDescription = productDescription;
    }

    @NotNull
    public Integer getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(@NotNull Integer amountInStock) {
        this.amountInStock = amountInStock;
    }

    public String getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(String inhabitants) {
        this.inhabitants = inhabitants;
    }

    public ProductDto(int id, @NotNull String productName, @NotNull Integer idCategory, @NotNull Integer price, @NotNull String productDescription, @NotNull Integer amountInStock, byte[] productImage, @NotNull String inhabitants) {
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
