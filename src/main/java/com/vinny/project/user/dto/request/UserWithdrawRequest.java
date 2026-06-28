package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserWithdrawRequest {
    @NotNull
    @Max(6)
    private int withdrawReasonType;

    private String withdrawReasonDetail;

}
