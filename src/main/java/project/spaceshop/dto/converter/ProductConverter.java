package project.spaceshop.dto.converter;

import org.springframework.stereotype.Component;
import project.spaceshop.dto.ProductDto;
import project.spaceshop.entity.Category;
import project.spaceshop.entity.Product;
import project.spaceshop.service.api.CategoryService;

@Component
public class ProductConverter {

    private CategoryService categoryService;
    private Category category;

    public Product fromProductDtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setCategory(categoryService.findCategoryById(productDto.getIdCategory()));
        product.setPrice(productDto.getPrice());
        product.setProductDescription(productDto.getProductDescription());
        product.setAmountInStock(productDto.getAmountInStock());
        product.setProductName(productDto.getProductImage());
        return product;
    }

    public ProductDto fromProductToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setIdCategory(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setAmountInStock(product.getAmountInStock());
        productDto.setProductImage(product.getProductImage());
        return productDto;
    }
}
