package com.vinny.project.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("존재하지 않는 아이디이다. id =" + id);
    }
}
