package com.vinny.project.user.exception;

import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;

public class UserNotWithdrawException extends BusinessException {
    public UserNotWithdrawException() {
        super(ErrorCode.USER_NOT_WITHDRAW_STATUS);
    }
}
