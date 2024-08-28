package com.example.test_datn.services;

import com.example.test_datn.dto.request.ProductRequest;
import com.example.test_datn.dto.response.ProductResponse;
import com.example.test_datn.dto.response.ResponsePage;
import com.example.test_datn.entity.Brand;
import com.example.test_datn.entity.Product;
import com.example.test_datn.entity.Status;
import com.example.test_datn.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {

    Product createProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    ResponsePage<Product , ProductResponse> getProductsPage(Pageable pageable);

    List<ProductResponse> getProductsList();

    List<SubCategory> getSubCategoriesList();

    List<Brand> getBrandList();

    List<Status> getStatusList();

    String deleteProduct(Long id);

    ResponsePage<Product , ProductResponse> filterProduct(Long subCate, Long status, Long brand, Pageable pageable);
}
