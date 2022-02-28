package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "products", schema = "planetshop")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int id;

    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "price")
    private Integer price;

    @Lob
    @Column(name = "image")
    private byte[] productImage;

    @Column(name = "description")
    private String productDescription;

    @Column(name = "amount_in_stock")
    private Integer amountInStock;

    @Column(name = "available")
    private boolean available = true;

    @Column(name = "inhabitants")
    private String inhabitants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInOrder> productsInOrder = new ArrayList<>();

    private int amountInBasket;

    public Product() {

    }

    public Product(int id, String productName, Category category, Integer price, byte[] productImage, String productDescription, Integer amountInStock, boolean available, String inhabitants, List<ProductInOrder> productsInOrder, int amountInBasket) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.amountInStock = amountInStock;
        this.available = available;
        this.inhabitants = inhabitants;
        this.productsInOrder = productsInOrder;
        this.amountInBasket = amountInBasket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
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
        if (amountInStock >= 0){
            this.amountInStock = amountInStock;
        } else {
            this.amountInStock = 0;
        }

    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(String inhabitants) {
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

