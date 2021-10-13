package project.spaceshop.service.api;

import project.spaceshop.dto.ProductDto;
import project.spaceshop.dto.ProductSendDto;
import project.spaceshop.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product findProductById(int id);

    Product saveProduct(Product product);

    List<Product> findProductByCategory(int id);

    Product createProduct(ProductDto productDto);

    List<Product> filter(int idCategory);
}
