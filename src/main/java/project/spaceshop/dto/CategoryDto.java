package project.spaceshop.dto;

import org.jetbrains.annotations.NotNull;

public class CategoryDto {

    private int id;

    private String categoryName;

    public CategoryDto() {
        this.categoryName = "";
    }

    public CategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
