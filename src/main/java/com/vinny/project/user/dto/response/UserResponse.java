package com.vinny.project.user.dto.response;

import com.vinny.project.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserResponse {
    private String email;
    private String nickname;
    private String profileImageUrl;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }
}
