package com.example.test_datn.exception;

import com.example.test_datn.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = AddException.class)
    ResponseEntity<ApiResponse> exception(AddException addException){
        EnumErrorException addError = addException.getEnumErrorException();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(addError.getCode());
        apiResponse.setMessage(addError.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> exception1(MethodArgumentNotValidException methodArgumentNotValid){
        String enumKey = methodArgumentNotValid.getFieldError().getDefaultMessage();
        EnumErrorException errorCode = EnumErrorException.KEY_NOT_FOUND;
        try {
            errorCode = EnumErrorException.valueOf(enumKey);
        }catch (IllegalArgumentException e){
            log.error("Lỗi: {}",enumKey);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
