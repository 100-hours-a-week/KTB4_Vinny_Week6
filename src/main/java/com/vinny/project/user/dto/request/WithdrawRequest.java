package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawRequest {
    @NotNull
    @Max(6)
    private int withdrawReasonType;

    private String withdrawReasonDetail;

}
