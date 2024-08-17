package com.example.test_datn.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddException extends RuntimeException{

    private EnumErrorException enumErrorException;

    public AddException(EnumErrorException enumErrorException) {
        super(enumErrorException.getMessage());
        this.enumErrorException = enumErrorException;
    }
}
