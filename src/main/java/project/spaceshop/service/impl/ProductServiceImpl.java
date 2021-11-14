package project.spaceshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.converter.ProductConverter;
import project.spaceshop.entity.Category;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.entity.Product;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductInOrderServiceImpl.class);

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
        public Product saveProduct(Product product) {
        log.info("new product " + product.getProductName() + " saved");
        return productRepository.save(product);

    }


    @Override
    public List<Product> findProductByCategory(int id) {
        return categoryService.findCategoryById(id).getProducts();
    }

    @Override
    public List<Product> filter(int idCategory) {
        List<Product> list;
        if (idCategory == 0) list = findAllProducts();
        else list = findProductByCategory(idCategory);
        return list;
    }
}
