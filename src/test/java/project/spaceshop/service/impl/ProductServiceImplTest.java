package project.spaceshop.service.impl;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.Product;
import project.spaceshop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public Product initProduct() {
        Product product = new Product();
        product.setProductName("Product");
        product.setAmountInStock(1);
        product.setPrice(100);
        return product;
    }

    @Test
    void findAllProducts() {
        List<Product> products = new ArrayList<>();
        Product product = initProduct();
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        productService.findAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    void findProductById() {
        Product product = initProduct();
        when(productRepository.getById(product.getId())).thenReturn(product);
        Product result = productService.findProductById(product.getId());
        verify(productRepository).getById(product.getId());
        assertEquals(product, result);
    }

    @Test
    void findProductById2() {
        Product product = initProduct();
        when(productRepository.getById(product.getId())).thenReturn(product);
        Product result = productService.findProductById(2);
        assertEquals(null, result);
    }

    @Test
    void saveProduct() {
        Product product = initProduct();
        when(productRepository.save(product)).thenReturn(product);
        Product result = productService.saveProduct(product);
        verify(productRepository).save(product);
    }

    @Test
    void findProductByCategory() {
        Product product = initProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        Category category = new Category();
        category.setProducts(products);
        product.setCategory(category);

        when(categoryService.findCategoryById(category.getId())).thenReturn(category);
        List<Product> selectedList = productService.findProductByCategory(category.getId());
        assertEquals(products, selectedList);

    }

    @Test
    void filterAllProducts() {
        Product product = initProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        Category category = new Category();
        category.setProducts(products);
        product.setCategory(category);

        when(productService.findAllProducts()).thenReturn(products);
        List<Product> selectedList = productService.filter(0);
        assertEquals(products, selectedList);

    }

}