package com.vinny.project.post;

import com.vinny.project.comment.Comment;
import com.vinny.project.enums.PostStatus;
import com.vinny.project.post.image.PostImage;
import com.vinny.project.post.like.PostLike;
import com.vinny.project.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
@SQLRestriction("status = 0")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_image_id")
    private PostImage mainImage;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "TINYINT")
    private PostStatus status;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private int likeCount = 0;

    @Column(nullable = false)
    private int commentCount = 0;

    @Column(nullable = false)
    private int viewCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> postImages = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostLike> likes = new ArrayList<>();

    protected Post() {}

    @Builder
    public Post(String title, String content, PostImage mainImageId, PostStatus status, User author) {
        this.title = title;
        this.content = content;
        this.mainImage = mainImageId;
        this.status = status;
        this.author = author;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeMainImage(PostImage mainImage) {
        this.mainImage = mainImage;
    }

    public void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void deletePost() {
        this.status = PostStatus.DELETED;
        this.deletedAt = LocalDateTime.now();

        for (PostImage image : this.postImages) {
            image.deleteImage();
        }

        for (Comment comment : this.comments) {
            comment.deleteComment();
        }
    }

    public void updateLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void updateCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }
}
