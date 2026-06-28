package com.vinny.project.post;

import com.vinny.project.post.dto.request.PostCreateRequest;
import com.vinny.project.post.dto.request.PostUpdateRequest;
import com.vinny.project.post.dto.response.PostDetailResponse;
import com.vinny.project.post.dto.response.PostListResponse;
import com.vinny.project.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
//@RequestMapping("/posts") 토큰 생기기 전까지 주석 처리
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/users/{userId}/posts")
    public ApiResponse<PostDetailResponse> createPost(
            @PathVariable Long userId,
            @Valid @ModelAttribute PostCreateRequest request
            ) {
        return ApiResponse.success(postService.createPost(userId,request));
    }

    @GetMapping("/posts")
    public ApiResponse<List<PostListResponse>> getPosts(){
        return ApiResponse.success(postService.getPosts());
    }

    @GetMapping("/posts/{postId}")
    public ApiResponse<PostDetailResponse> getPost(@PathVariable Long postId) {
        return ApiResponse.success(postService.getPost(postId));
    }

    @PatchMapping("/posts/{postId}")
    public ApiResponse<PostDetailResponse> updatePost(
            @PathVariable Long postId,
            @Valid @ModelAttribute PostUpdateRequest request
    ) {
        return ApiResponse.success(postService.patch(postId, request));
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ApiResponse.success(null);
    }
}
