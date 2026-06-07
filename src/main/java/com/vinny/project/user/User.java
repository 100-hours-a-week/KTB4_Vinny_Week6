package com.vinny.project.user;

import java.util.UUID;

public class User {

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String profileImageUrl;

    public User(String id, String email, String password, String nickname, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getNickname(){
        return nickname;
    }

    public String getProfileImageUrl(){
        return profileImageUrl;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }
}
