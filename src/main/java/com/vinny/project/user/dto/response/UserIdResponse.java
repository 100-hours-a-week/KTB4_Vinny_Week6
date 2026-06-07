package com.vinny.project.user.dto.response;

import com.vinny.project.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserIdResponse {
    private String id;

    public UserIdResponse(User user){
        this.id = user.getId();
    }
}
