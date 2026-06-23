package com.vinny.project.comment;

import com.vinny.project.enums.CommentStatus;
import com.vinny.project.post.Post;
import com.vinny.project.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction("status = 0")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "TINYINT")
    private CommentStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne()
    @JoinColumn(name = "parent_comment_id", unique = true)
    private Comment parentComment;

    protected Comment() {}

    @Builder
    public Comment(String content, CommentStatus status, User author, Post post,  Comment parentComment) {
        this.content = content;
        this.status = status;
        this.author = author;
        this.post = post;
        this.parentComment = parentComment;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void deleteComment() {
        this.status = CommentStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
    }
}
