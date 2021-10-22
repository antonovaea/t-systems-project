package project.spaceshop.service.api;

import org.springframework.data.domain.Page;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.dto.ProductSendDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> findPaginated(int pageNo, int pageSize);

    List<Product> findAllProducts();

    Product findProductById(int id);

    Product saveProduct(Product product);

    List<Product> findProductByCategory(int id);

    List<Product> filter(int idCategory);
}
