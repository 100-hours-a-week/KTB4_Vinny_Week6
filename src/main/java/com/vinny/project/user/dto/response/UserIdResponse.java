package com.vinny.project.user.dto.response;

import lombok.Getter;

@Getter
public class UserIdResponse {
    private Long userId;

    public UserIdResponse(Long userId){
        this.userId = userId;
    }
}
