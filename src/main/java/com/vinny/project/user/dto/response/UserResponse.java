package com.vinny.project.user.dto.response;

import com.vinny.project.user.User;

public class UserResponse {
    private String email;
    private String nickname;
    private String profileImageUrl;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
