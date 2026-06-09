package com.vinny.project.post.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinny.project.user.dto.response.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponse {
    private String postId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private UserSummary writer;
}
