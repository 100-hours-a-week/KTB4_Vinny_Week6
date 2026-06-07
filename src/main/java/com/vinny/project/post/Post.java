package com.vinny.project.post;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class Post {
    private String writerId;
    private String postId;
    private String title;
    private String content;
    private String postImageUrl;
    private LocalDateTime createdAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;

    public Post(String writerId, String postId, String title, String content, String postImageUrl, LocalDateTime createdAt, int likeCount, int commentCount, int viewCount ) {
        this.writerId = writerId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postImageUrl = postImageUrl;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(String postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

}
