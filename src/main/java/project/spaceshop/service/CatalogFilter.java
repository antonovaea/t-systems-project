package project.spaceshop.service;

import org.springframework.stereotype.Component;

@Component
public class CatalogFilter {

    private int idCategory = 0;

    public CatalogFilter() {
    }

    public CatalogFilter(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

}
