package com.example.test_datn.exception;

import com.longnh.exceptions.BaseErrorMessage;

public enum ErrorMessage implements BaseErrorMessage {
    BRAND_NOT_FOUND("Brand not found"),
    STATUS_NOT_FOUND("Status not found"),
    SUBCATEGORY_NOT_FOUND("Subcategory not found"),
    PRODUCT_ID_NOT_FOUND("Product ID not found"),
    ;


    private String val;

    private ErrorMessage(String label) {
        val = label;
    }

    @Override
    public String val() {
        return val;
    }

}
