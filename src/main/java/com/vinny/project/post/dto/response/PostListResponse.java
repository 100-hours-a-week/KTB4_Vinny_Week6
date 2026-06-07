package com.vinny.project.post.dto.response;

import com.vinny.project.post.Post;
import com.vinny.project.user.dto.response.UserResponse;

public class PostListResponse {
    private String postId;
    private String title;
    private String createdAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private UserResponse writer;

    public PostListResponse(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt().toString();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.viewCount = post.getViewCount();
        //this.writer =
    }
}
