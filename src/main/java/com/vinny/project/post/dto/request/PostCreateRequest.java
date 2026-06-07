package com.vinny.project.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PostCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    //@NotBlank
    private String postImageUrl;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPostImageUrl() {
        return postImageUrl;
    }
}
