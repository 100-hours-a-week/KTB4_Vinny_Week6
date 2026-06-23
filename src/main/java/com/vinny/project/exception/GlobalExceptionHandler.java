package com.vinny.project.exception;

import com.vinny.project.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e){
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).body(ApiResponse.fail(errorCode));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().getFirst().getDefaultMessage();

        return ResponseEntity.badRequest().body(ApiResponse.fail(errorMessage));
    }
}
