package com.vinny.project.user.exception;

import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;

public class DuplicateNickname extends BusinessException {
    public DuplicateNickname() {
        super(ErrorCode.DUPLICATE_NICKNAME);
    }
}
