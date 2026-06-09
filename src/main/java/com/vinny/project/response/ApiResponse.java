package com.vinny.project.response;

import com.vinny.project.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(false, null, errorCode.getMessage());
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, null, message);
    }
}