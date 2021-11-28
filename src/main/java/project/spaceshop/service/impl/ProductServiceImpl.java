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

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ProductInOrderServiceImpl.class);

    /**
     * Injected by spring ProductRepository bean
     */
    private final ProductRepository productRepository;

    /**
     * Injected by spring CategoryService bean
     */
    private final CategoryService categoryService;

    /**
     * Injected constructor
     *
     * @param productRepository that must be injected.
     * @param categoryService   that must be injected.
     */
    @Autowired
    ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    /**
     * Method divides list of existing products on pages.
     *
     * @param pageNo   page number.
     * @param pageSize number of showing products on one page.
     * @return pages with existing products.
     */
    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.productRepository.findAll(pageable);
    }

    /**
     * Method looks for all existing products.
     *
     * @return all products existing in database.
     */
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Method looks for product by id.
     *
     * @param id param id of required product.
     * @return found product.
     */
    @Override
    public Product findProductById(int id) {
        return productRepository.getById(id);
    }

    /**
     * Method adds product to database.
     *
     * @param product product that must be saved.
     * @return saved product.
     */
    @Override
    public Product saveProduct(Product product) {
        log.info("new product " + product.getProductName() + " saved");
        return productRepository.save(product);

    }

    /**
     * Method looks for products by category.
     *
     * @param id param id of required category.
     * @return all existing product belonging to the category.
     */
    @Override
    public List<Product> findProductByCategory(int id) {
        return categoryService.findCategoryById(id).getProducts();
    }

    /**
     * Method filters products by category.
     *
     * @param idCategory param id of required category.
     * @return existing product belonging to the category.
     */
    @Override
    public List<Product> filter(int idCategory) {
        List<Product> list;
        if (idCategory == 0) list = findAllProducts();
        else list = findProductByCategory(idCategory);
        return list;
    }
}
