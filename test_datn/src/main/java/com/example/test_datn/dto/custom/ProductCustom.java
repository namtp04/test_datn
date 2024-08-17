package com.example.test_datn.dto.custom;

import com.example.test_datn.entity.Brand;
import com.example.test_datn.entity.Status;
import com.example.test_datn.entity.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustom {

    private String productName;

    private Long quantity;

    private String color;

    private Double originPrice;

    private Double sellPrice;

    private String brandName;

    private String subCateName;

    private String statusName;
}
