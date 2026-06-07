package com.vinny.project.post;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class Post {
    private String writerId;
    private String postId;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String postImageUrl;
    private LocalDateTime createdAt;
    @Setter
    private int likeCount;
    @Setter
    private int commentCount;
    @Setter
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
}
