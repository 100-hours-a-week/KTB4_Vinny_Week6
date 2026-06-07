package com.vinny.project.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Comment {
    private String writerId;
    private String postId;
    private String commentId;
    @Setter
    private String content;
    private LocalDateTime createdAt;

    public Comment(String writerId,String postId, String commentId, String content, LocalDateTime createdAt) {
        this.writerId = writerId;
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
