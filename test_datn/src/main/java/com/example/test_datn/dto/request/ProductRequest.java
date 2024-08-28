package com.example.test_datn.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "PRODUCT_NAME_NOT_FOUND")
    private String productName;

    @NotNull(message = "QUANTITY_NOT_FOUND")
    @Positive(message = "QUANTITY_NOT_NEGATIVE")
    private Long quantity;

    @NotBlank(message = "COLOR_NOT_FOUND")
    private String color;

    @NotNull(message = "ORIGIN_PRICE_NOT_FOUND")
    @Positive(message = "ORIGIN_PRICE_NOT_NEGATIVE")
    private Double originPrice;

    @NotNull(message = "SELL_PRICE_NOT_FOUND")
    @Positive(message = "SELL_PRICE_NOT_NEGATIVE")
    private Double sellPrice;

    @NotNull(message = "BRAND_NOT_FOUND")
    private Long brandListId;

    @NotNull(message = "SUBCATEGORY_NOT_FOUND")
    private Long subCateId;

    @NotNull(message = "STATUS_NOT_FOUND")
    private Long statusId;
}
