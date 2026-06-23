package com.vinny.project.user.exception;

import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;

public class LoginInputInvalidException extends BusinessException {
    public LoginInputInvalidException() {
        super(ErrorCode.LOGIN_INPUT_INVALID);
    }
}
