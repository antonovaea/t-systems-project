package project.spaceshop.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "top_categories", schema = "planetshop")
public class TopCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_top_category")
    private Integer id;

    @Column(name = "top_category_name")
    private String topCategoryName;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "amount_of_sold_products")
    private Integer amountOfSoldProducts;

    public TopCategory() {

    }

    public TopCategory(int id, @NotNull Category category, Integer amountOfSoldProducts) {
        this.id = id;
        this.category = category;
        this.amountOfSoldProducts = amountOfSoldProducts;
    }

    public TopCategory(String topCategoryName, @NotNull Category category, Integer amountOfSoldProducts) {
        this.topCategoryName = topCategoryName;
        this.category = category;
        this.amountOfSoldProducts = amountOfSoldProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopCategoryName() {
        return topCategoryName;
    }

    public void setTopCategoryName(String topCategoryName) {
        this.topCategoryName = topCategoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getAmountOfSoldProducts() {
        return amountOfSoldProducts;
    }

    public void setAmountOfSoldProducts(Integer amountOfSoldProducts) {
        this.amountOfSoldProducts = amountOfSoldProducts;
    }

    @Override
    public String toString() {
        return "TopCategory{" +
                "id=" + id +
                ", topCategoryName='" + topCategoryName + '\'' +
                ", amountOfSoldProducts=" + amountOfSoldProducts +
                '}';
    }
}
