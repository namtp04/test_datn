package com.example.test_datn.services;

import com.example.test_datn.dto.request.ProductRequest;
import com.example.test_datn.dto.response.ProductResponse;
import com.example.test_datn.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    Page<ProductResponse> getProductsPage(Pageable pageable);

    List<ProductResponse> getProductsList();

    String deleteProduct(Long id);
}
