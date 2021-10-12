package project.spaceshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spaceshop.dto.converter.ProductConverter;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.entity.Product;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.service.api.CategoryService;
import project.spaceshop.service.api.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductConverter productConverter;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> list = productRepository.findAll();
        List<ProductDto> listDto = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            listDto.add(productConverter.fromProductToProductDto(list.get(i)));
        }
        return listDto;
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.getById(id);
    }

    @Override
        public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductDto> findProductByCategory(int id) {
        List<Product> list = categoryService.findCategoryById(id).getProducts();
        List<ProductDto> listDto = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            listDto.add(productConverter.fromProductToProductDto(list.get(i)));
        }
        return listDto;
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = productConverter.fromProductDtoToProduct(productDto);
        product = saveProduct(product);
        return product;
    }

    @Override
    public List<ProductDto> filter(int idCategory) {
        List<ProductDto> productsDto;
        if (idCategory == 0) productsDto = findAllProducts();
        else productsDto = findProductByCategory(idCategory);
        return productsDto;
    }
}
