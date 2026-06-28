package com.vinny.project.user.exception;

import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;

public class WithdrawPendingException extends BusinessException {
    public WithdrawPendingException() {
        super(ErrorCode.WITHDRAW_PENDING_EXCEPTION);
    }
}
