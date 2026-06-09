package com.vinny.project.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    @NotBlank(message = "제목은 필수입니다.")
    @Size(min = 1, max = 26, message = "최대 26자까지 가능합니다.")
    private String title;

    @NotBlank(message ="내용을 작성해주세요.")
    private String content;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$", message = "이미지는 URL 형식이어야 합니다.")
    private String postImageUrl;
}
