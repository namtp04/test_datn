package com.example.test_datn.services.impl;

import com.example.test_datn.dto.request.ProductRequest;
import com.example.test_datn.dto.response.ProductResponse;
import com.example.test_datn.entity.Brand;
import com.example.test_datn.entity.Product;
import com.example.test_datn.entity.Status;
import com.example.test_datn.entity.SubCategory;
import com.example.test_datn.exception.AddException;
import com.example.test_datn.exception.EnumErrorException;
import com.example.test_datn.repository.BrandRepository;
import com.example.test_datn.repository.ProductRepository;
import com.example.test_datn.repository.StatusRepository;
import com.example.test_datn.repository.SubCategoryRepository;
import com.example.test_datn.services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product productCreate = new Product();
        Product product = mapProductRequestToProduct(productCreate, productRequest);
        productRepository.save(product);
        return mapProductToProductResponse(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()->{
            throw new AddException(EnumErrorException.PRODUCT_ID_NOT_FOUND);
        });
        return mapProductToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product productById = productRepository.findById(id).orElseThrow(()->{
            throw new AddException(EnumErrorException.PRODUCT_ID_NOT_FOUND);
        });
        Product product = mapProductRequestToProduct(productById, productRequest);
        productRepository.save(product);
        return mapProductToProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProductsPage(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products.getContent().stream()
                .map(product -> mapProductToProductResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
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
        Product product = productRepository.findById(id).orElseThrow(()->{
            throw new AddException(EnumErrorException.PRODUCT_ID_NOT_FOUND);
        });
        productRepository.delete(product);
        return "Product deleted successfully";
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setSellPrice(product.getSellPrice());
        productResponse.setOriginPrice(product.getOriginPrice());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setColor(product.getColor());
        productResponse.setStatus(product.getStatus());
        productResponse.setSubCate(product.getSubCate());
        productResponse.setBrandList(product.getBrandList());
        return productResponse;
    }

    private Product mapProductRequestToProduct(Product product, ProductRequest productRequest) {
        SubCategory subCategory = subCategoryRepository.findById(productRequest.getSubCateId())
                .orElseThrow(() -> {
                    throw new AddException(EnumErrorException.SUBCATEGORY_NOT_FOUND);
                });

        Status status = statusRepository.findById(productRequest.getStatusId())
                .orElseThrow(() -> {
                    throw new AddException(EnumErrorException.STATUS_NOT_FOUND);
                });

        if(brandRepository.findAllById(productRequest.getBrandListId()).isEmpty()){
            throw new AddException(EnumErrorException.BRAND_NOT_FOUND);
        }

        List<Brand> brandList = brandRepository.findAllById(productRequest.getBrandListId());
        ;
        product.setProductName(productRequest.getProductName());
        product.setSellPrice(productRequest.getSellPrice());
        product.setOriginPrice(productRequest.getOriginPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setColor(productRequest.getColor());
        product.setStatus(status);
        product.setSubCate(subCategory);
        product.setBrandList(brandList);
        return product;
    }
}
