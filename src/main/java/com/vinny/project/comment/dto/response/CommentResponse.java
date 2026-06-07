package com.vinny.project.comment.dto.response;

import com.vinny.project.user.dto.response.UserResponse;

import java.time.LocalDateTime;

public class CommentResponse {
    private String id;
    private String content;
    private LocalDateTime createdAt;
    private UserResponse user;
}
