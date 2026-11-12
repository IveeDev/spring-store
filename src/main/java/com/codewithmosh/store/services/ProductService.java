package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.exception.CategoryNotFoundException;
import com.codewithmosh.store.exception.ProductNotFoundException;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    public List<ProductDto> getAllProducts(Byte categoryId){
        List<Product> products;
        if(categoryId != null){
            products = productRepository.findByCategoryId(categoryId);
        }else{
            products = productRepository.findAllWithCategory();
        }
        return products.stream().map(product -> productMapper.toDto(product)).toList();
    }

    public ProductDto getProduct(Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }
        return productMapper.toDto(product);
    }

    public ProductDto createProduct(ProductDto productDto){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            throw new CategoryNotFoundException();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);

        productDto.setId(product.getId());
        return productDto;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto){
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            throw new CategoryNotFoundException();
        }

        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }

        productMapper.updateEntity(productDto, product);
        product.setCategory(category);
        productRepository.save(product);

        productDto.setId(product.getId());
        return productDto;
    }

    public void deleteProduct(Long id){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            throw new ProductNotFoundException();
        }
        productRepository.delete(product);
    }
}