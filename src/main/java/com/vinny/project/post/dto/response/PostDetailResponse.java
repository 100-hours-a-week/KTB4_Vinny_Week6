package com.vinny.project.post.dto.response;

import com.vinny.project.post.Post;
import com.vinny.project.response.ApiResponse;
import com.vinny.project.user.dto.response.UserResponse;

//상속 고민
public class PostDetailResponse {
    private String postId;
    private String title;
    private String content;
    private String createdAt;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private boolean isOwner;
    private UserResponse writer;

    public PostDetailResponse(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt().toString();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.viewCount = post.getViewCount();
        //this.isOwner =
    }
}
