package com.example.test_datn.dto.response;

import com.example.test_datn.entity.Brand;
import com.example.test_datn.entity.Status;
import com.example.test_datn.entity.SubCategory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private Long quantity;

    private String color;

    private Double originPrice;

    private Double sellPrice;

    private List<Brand> brandList;

    private SubCategory subCate;

    private Status status;
}
