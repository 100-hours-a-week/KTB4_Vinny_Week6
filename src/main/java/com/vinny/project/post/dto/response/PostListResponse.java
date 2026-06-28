package com.vinny.project.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.vinny.project.post.Post;

import com.vinny.project.user.dto.response.AuthorSummary;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;
    private String title;
    private String mainImageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private AuthorSummary author;

    @Builder
    private PostListResponse(Long postId, String title, String mainImageUrl, LocalDateTime createdAt,
                             LocalDateTime updatedAt, int likeCount, int commentCount, int viewCount, AuthorSummary author) {
        this.postId = postId;
        this.title = title;
        this.mainImageUrl = mainImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.author = author;
    }

    public static PostListResponse of(Post post, AuthorSummary author) {

        return PostListResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .mainImageUrl(post.getMainImage().getImageUrl())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .viewCount(post.getViewCount())
                .author(author)
                .build();
    }
}
