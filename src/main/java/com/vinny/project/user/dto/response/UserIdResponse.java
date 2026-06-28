package com.vinny.project.user.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;

@Getter
public class UserIdResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    public UserIdResponse(Long userId){
        this.userId = userId;
    }
}
