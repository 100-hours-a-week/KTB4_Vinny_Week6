package com.vinny.project.post.like;

import com.vinny.project.post.Post;
import com.vinny.project.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="post_likes",
        uniqueConstraints = {
            @UniqueConstraint(
                name = "uq_post_likes_user_post",
                columnNames = {"user_id", "post_id"}
            )
        }
    )
@EntityListeners(AuditingEntityListener.class)
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    protected PostLike() {}

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
