package com.vinny.project.user.exception;

import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;

public class AuthPasswordMismatchException extends BusinessException {
    public AuthPasswordMismatchException() {
        super(ErrorCode.AUTH_PASSWORD_MISMATCH);
    }
}
