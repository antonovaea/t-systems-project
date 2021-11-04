package project.spaceshop.dto;

public class TopCategoryDto {

    private Integer id;

    private String topCategoryName;

    private Integer amountOfSoldProducts;

    public TopCategoryDto(Integer id, String topCategoryName, Integer amountOfSoldProducts) {
        this.id = id;
        this.topCategoryName = topCategoryName;
        this.amountOfSoldProducts = amountOfSoldProducts;
    }

    public TopCategoryDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopCategoryName() {
        return topCategoryName;
    }

    public void setTopCategoryName(String topCategoryName) {
        this.topCategoryName = topCategoryName;
    }

    public Integer getAmountOfSoldProducts() {
        return amountOfSoldProducts;
    }

    public void setAmountOfSoldProducts(Integer amountOfSoldProducts) {
        this.amountOfSoldProducts = amountOfSoldProducts;
    }

    @Override
    public String toString() {
        return "TopCategoryDto{" +
                "id=" + id +
                ", topCategoryName='" + topCategoryName + '\'' +
                ", amountOfSoldProducts=" + amountOfSoldProducts +
                '}';
    }
}
