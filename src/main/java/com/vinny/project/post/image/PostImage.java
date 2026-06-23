package com.vinny.project.post.image;

import com.vinny.project.enums.PostStatus;
import com.vinny.project.post.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "post_images")
@EntityListeners(AuditingEntityListener.class)
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private int sequence;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "TINYINT")
    private PostStatus status;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    protected PostImage() {}

    @Builder
    public PostImage(String imageUrl, int sequence, PostStatus status, Post post) {
        this.imageUrl = imageUrl;
        this.sequence = sequence;
        this.status = status;
        this.post = post;
    }

    public void deleteImage() {
        this.status = PostStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
    }

}
