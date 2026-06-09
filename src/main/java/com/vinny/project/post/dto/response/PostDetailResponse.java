package com.vinny.project.post.dto.response;

import com.vinny.project.post.Post;
import com.vinny.project.response.ApiResponse;
import com.vinny.project.user.dto.response.UserResponse;
import com.vinny.project.user.dto.response.UserSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostDetailResponse extends PostListResponse {
    private String content;

    public PostDetailResponse(String postId, String title, LocalDateTime createdAt, int likeCount, int commentCount, int viewCount, UserSummary writer, String content) {
        super(postId, title, createdAt, likeCount, commentCount, viewCount, writer);
        this.content = content;
    }
}
