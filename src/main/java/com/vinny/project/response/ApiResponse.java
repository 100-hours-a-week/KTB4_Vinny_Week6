package com.vinny.project.response;

import com.vinny.project.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;

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