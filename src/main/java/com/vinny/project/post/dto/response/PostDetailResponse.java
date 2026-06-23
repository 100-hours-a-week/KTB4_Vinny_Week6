package com.vinny.project.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinny.project.post.Post;
import com.vinny.project.post.image.PostImageResponse;
import com.vinny.project.user.dto.response.AuthorSummary;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponse {
    private Long postId;
    private String title;
    private String content;
    private List<PostImageResponse> images;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private AuthorSummary author;

    @Builder
    private PostDetailResponse(Long postId, String title, String content, List<PostImageResponse> images,
                               LocalDateTime createdAt, LocalDateTime updatedAt, int likeCount,
                               int commentCount, int viewCount, AuthorSummary author) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.images = images;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.author = author;
    }

    public static PostDetailResponse of(Post post, AuthorSummary author, List<PostImageResponse> images) {

        return PostDetailResponse.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .images(images)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .likeCount(post.getLikeCount())
                .commentCount(post.getCommentCount())
                .viewCount(post.getViewCount())
                .author(author)
                .build();
    }


}
