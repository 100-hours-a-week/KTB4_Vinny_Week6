package com.vinny.project.post.image;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImageResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String imageUrl;
    private int sequence;

    @Builder
    private PostImageResponse(Long id, String imageUrl, int sequence) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.sequence = sequence;
    }

    public static PostImageResponse from(PostImage postImage) {
        return PostImageResponse.builder()
                .id(postImage.getImageId())
                .imageUrl(postImage.getImageUrl())
                .sequence(postImage.getSequence())
                .build();
    }
}
