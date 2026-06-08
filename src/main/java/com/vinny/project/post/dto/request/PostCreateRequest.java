package com.vinny.project.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$", message = "이미지는 default.png 또는 URL 형식이어야 합니다.")
    private String postImageUrl;
}
