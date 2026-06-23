package com.vinny.project.comment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinny.project.comment.Comment;
import com.vinny.project.comment.exception.CommentNotFoundException;
import com.vinny.project.user.dto.response.AuthorSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private AuthorSummary author;

    @Builder
    private CommentResponse(Long commentId, String content, LocalDateTime createdAt, LocalDateTime updatedAt, AuthorSummary author) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public static CommentResponse from(Comment comment) {
        if(comment == null) { throw new CommentNotFoundException();}

        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .author(AuthorSummary.from(comment.getAuthor()))
                .build();
    }
}
