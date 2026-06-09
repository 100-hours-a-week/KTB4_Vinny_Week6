package com.vinny.project.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String writerId;
    private String postId;
    private String commentId;
    @Setter
    private String content;
    private LocalDateTime createdAt;
}
