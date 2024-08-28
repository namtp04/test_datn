package com.example.test_datn.services.impl;

import com.example.test_datn.dto.request.ProductRequest;
import com.example.test_datn.dto.response.ProductResponse;
import com.example.test_datn.dto.response.ResponsePage;
import com.example.test_datn.entity.Brand;
import com.example.test_datn.entity.Product;
import com.example.test_datn.entity.Status;
import com.example.test_datn.entity.SubCategory;
import com.example.test_datn.exception.ErrorMessage;
import com.example.test_datn.repository.BrandRepository;
import com.example.test_datn.repository.ProductRepository;
import com.example.test_datn.repository.StatusRepository;
import com.example.test_datn.repository.SubCategoryRepository;
import com.example.test_datn.services.ProductService;
import com.longnh.exceptions.ExceptionHandle;
import com.longnh.utils.FnCommon;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    SubCategoryRepository subCategoryRepository;

    StatusRepository statusRepository;

    BrandRepository brandRepository;

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product productCreate = new Product();
        Product product = mapProductRequestToProduct(productCreate, productRequest);
        return productRepository.save(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_ID_NOT_FOUND);
        });
        return mapProductToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product productById = productRepository.findById(id).orElseThrow(() -> {
            throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_ID_NOT_FOUND);
        });
        Product product = mapProductRequestToProduct(productById, productRequest);
        productRepository.save(product);
        return mapProductToProductResponse(product);
    }

    @Override
    public ResponsePage<Product, ProductResponse> getProductsPage(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        return new ResponsePage<>(productsPage, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getProductsList() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> mapProductToProductResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_ID_NOT_FOUND);
        });
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    @Override
    public ResponsePage<Product, ProductResponse> filterProduct(Long subCate, Long status, Long brand, Pageable pageable) {
        List<Product> products = productRepository.findAll();
        if (subCate != null) {
            products = products.stream()
                    .filter(p -> p.getSubCate().getId().equals(subCate))
                    .collect(Collectors.toList());
        }
        if (status != null) {
            products = products.stream()
                    .filter(p -> p.getStatus().getId().equals(status))
                    .collect(Collectors.toList());
        }
        if (brand != null) {
            products = products.stream()
                    .filter(p -> p.getBrandList().stream()
                            .anyMatch(b -> b.getId().equals(brand)))
                    .collect(Collectors.toList());
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());
        List<Product> productList = products.subList(start, end);
        Page<Product> productsPage = new PageImpl<>(productList, pageable, products.size());
        return new ResponsePage<>(productsPage, ProductResponse.class);
    }

    @Override
    public List<SubCategory> getSubCategoriesList() {
        return subCategoryRepository.findAll();
    }

    @Override
    public List<Brand> getBrandList() {
        return brandRepository.findAll();
    }

    @Override
    public List<Status> getStatusList() {
        return statusRepository.findAll();
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        FnCommon.copyNonNullProperties(productResponse, product);
        productResponse.setBrandList(product.getBrandList());
        productResponse.setStatus(product.getStatus());
        productResponse.setSubCate(product.getSubCate());
        return productResponse;
    }

    private Product mapProductRequestToProduct(Product product, ProductRequest productRequest) {
        SubCategory subCategory = subCategoryRepository.findById(productRequest.getSubCateId())
                .orElseThrow(() -> {
                    throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.SUBCATEGORY_NOT_FOUND);
                });

        Status status = statusRepository.findById(productRequest.getStatusId())
                .orElseThrow(() -> {
                    throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.STATUS_NOT_FOUND);
                });


        if (brandRepository.findById(productRequest.getBrandListId()).isEmpty()) {
            throw new ExceptionHandle(HttpStatus.NOT_FOUND, ErrorMessage.BRAND_NOT_FOUND);
        }

        List<Brand> brandList = new ArrayList<>();
        brandList.add(brandRepository.findById(productRequest.getBrandListId()).get())
        ;
        FnCommon.copyNonNullProperties(product, productRequest);
        product.setBrandList(brandList);
        product.setStatus(status);
        product.setSubCate(subCategory);
        return product;
    }
}
