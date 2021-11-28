package project.spaceshop.service.api;

import org.springframework.data.domain.Page;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.dto.ProductSendDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface provides API we can use to manipulate products.
 */
public interface ProductService {

    /**
     * Method divides list of existing products on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing products on one page.
     * @return pages with existing products.
     */
    Page<Product> findPaginated(int pageNo, int pageSize);

    /**
     * Method looks for all existing products.
     *
     * @return all products existing in database.
     */
    List<Product> findAllProducts();

    /**
     * Method looks for product by id.
     *
     * @param id param id of required product.
     * @return found product.
     */
    Product findProductById(int id);

    /**
     * Method adds product to database.
     *
     * @param product product that must be saved.
     * @return saved product.
     */
    Product saveProduct(Product product);

    /**
     * Method looks for products by category.
     *
     * @param id param id of required category.
     * @return all existing product belonging to the category.
     */
    List<Product> findProductByCategory(int id);

    /**
     * Method filters products by category.
     *
     * @param idCategory param id of required category.
     * @return existing product belonging to the category.
     */
    List<Product> filter(int idCategory);
}
