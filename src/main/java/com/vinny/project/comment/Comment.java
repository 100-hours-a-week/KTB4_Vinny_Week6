package com.vinny.project.comment;

import java.time.LocalDateTime;

public class Comment {
    private String writerId;
    private String postId;
    private String commentId;
    private String content;
    private LocalDateTime createdAt;

    public Comment(String writerId,String postId, String commentId, String content, LocalDateTime createdAt) {
        this.writerId = writerId;
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getPostId() {
        return postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
