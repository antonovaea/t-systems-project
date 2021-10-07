package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "products", schema = "planetshop")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int id;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Column(name = "image")
    private String productImage;

    @NotNull
    @Column(name = "description")
    private String productDescription;

    @NotNull
    @Column(name = "amount_in_stock")
    private Integer amountInStock;

    @NotNull
    @Column(name = "available")
    private boolean available = true;

    @NotNull
    @Column(name = "inhabitants")
    private String inhabitants;

    public Product() {

    }

    public Product(int id, @NotNull String productName, @NotNull Category category, @NotNull Integer price, @NotNull String productImage, @NotNull String productDescription, @NotNull Integer amountInStock, boolean available, @NotNull String inhabitants) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.amountInStock = amountInStock;
        this.available = available;
        this.inhabitants = inhabitants;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull String productName) {
        this.productName = productName;
    }

    @NotNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    @NotNull
    public Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull Integer price) {
        this.price = price;
    }

    @NotNull
    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(@NotNull String productImage) {
        this.productImage = productImage;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @NotNull
    public String getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(@NotNull String inhabitants) {
        this.inhabitants = inhabitants;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", amountInStock=" + amountInStock +
                ", available=" + available +
                ", inhabitants=" + inhabitants +
                '}';
    }


}

