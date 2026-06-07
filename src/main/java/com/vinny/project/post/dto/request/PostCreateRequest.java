package com.vinny.project.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String postImageUrl;
}
