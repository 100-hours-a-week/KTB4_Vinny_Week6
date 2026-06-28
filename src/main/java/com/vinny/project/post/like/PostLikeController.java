package com.vinny.project.post.like;

import com.vinny.project.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/users/{userId}/posts/{postId}/likes")
public class PostLikeController {

    private final PostLikeService postLikeService;
    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping
    public ApiResponse<PostLikeResponse> toggleLike(@PathVariable Long userId, @PathVariable Long postId) {
        return ApiResponse.success(postLikeService.toggleLike(userId, postId));
    }
}
