package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products", schema = "planetshop")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Lob
    @Column(name = "image")
    private byte[] productImage;

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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
//    private List<ProductInOrder> productsInOrder = new ArrayList<>();

    private int amountInBasket;

    public Product() {

    }

    public Product(int id, @NotNull String productName, @NotNull Category category, @NotNull Integer price, @NotNull byte[] productImage, @NotNull String productDescription, @NotNull Integer amountInStock, boolean available, @NotNull String inhabitants) {
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

    public Product(int id, @NotNull String productName, @NotNull Category category, @NotNull Integer price, byte[] productImage, @NotNull String productDescription, @NotNull Integer amountInStock, @NotNull boolean available, @NotNull String inhabitants, int amountInBasket) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.amountInStock = amountInStock;
        this.available = available;
        this.inhabitants = inhabitants;
        this.amountInBasket = amountInBasket;
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
    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(@NotNull byte[] productImage) {
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

    public int getAmountInBasket() {
        return amountInBasket;
    }

    public void setAmountInBasket(int amountInBasket) {
        this.amountInBasket = amountInBasket;
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

