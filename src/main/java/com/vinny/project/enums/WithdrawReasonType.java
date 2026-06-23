package com.vinny.project.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum WithdrawReasonType {
    NOT_USED(0),
    FEATURE_MISSING(1),
    INCONVENIENT(2),
    CONTENT_ISSUE(3),
    PRIVACY_CONCERN(4),
    FOUND_ALTERNATIVE(5),
    ETC(6);

    private final int code;
    WithdrawReasonType(int code) {
        this.code = code;
    }

    public static WithdrawReasonType ofCode(Integer code) {

        return Arrays.stream(WithdrawReasonType.values())
                .filter(v -> v.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 탈퇴 사유입니다"));
    }
}
