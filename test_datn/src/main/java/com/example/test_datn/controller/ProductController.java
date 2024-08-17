package com.example.test_datn.controller;

import com.example.test_datn.dto.request.ProductRequest;
import com.example.test_datn.dto.response.ApiResponse;
import com.example.test_datn.dto.response.ProductResponse;
import com.example.test_datn.entity.Product;
import com.example.test_datn.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProduct() {
        return productService.getProductsList();
    }

    @GetMapping("phan-trang")
    public Page<ProductResponse> getAllProductPage(Pageable pageable) {
        return productService.getProductsPage(pageable);
    }

    @PostMapping("add")
    public ProductResponse add(@RequestBody @Valid ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PutMapping("update/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @GetMapping("{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        String result = productService.deleteProduct(id);
        return ApiResponse.builder()
                .code(200)
                .message(result)
                .build();
    }
}
