package com.vinny.project.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {

    private String id;
    private String email;
    @Setter
    private String password;
    @Setter
    private String nickname;
    @Setter
    private String profileImageUrl;

    public User(String id, String email, String password, String nickname, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
