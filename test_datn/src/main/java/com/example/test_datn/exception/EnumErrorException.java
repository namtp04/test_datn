package com.example.test_datn.exception;

public enum EnumErrorException {
    SUBCATEGORY_NOT_FOUND(4000,"Subcategory not found"),
    STATUS_NOT_FOUND(4001,"Status not found"),
    BRAND_NOT_FOUND(4002,"Brand not found"),
    KEY_NOT_FOUND(4003,"Key not found"),
    PRODUCT_NAME_NOT_FOUND(4004,"Product not found"),
    QUANTITY_NOT_FOUND(4005,"Quality not found"),
    COLOR_NOT_FOUND(4006,"Color not found"),
    ORIGIN_PRICE_NOT_FOUND(4007,"Origin price not found"),
    SELL_PRICE_NOT_FOUND(4008,"Sell price not found"),
    PRODUCT_ID_NOT_FOUND(4009,"Product ID not found"),
    ;

    private final Integer code;

    private final String message;

    EnumErrorException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
