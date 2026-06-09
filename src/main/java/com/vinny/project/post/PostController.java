package com.vinny.project.post;

import com.vinny.project.post.dto.request.PostCreateRequest;
import com.vinny.project.post.dto.response.PostDetailResponse;
import com.vinny.project.post.dto.response.PostListResponse;
import com.vinny.project.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ApiResponse<Post> createPost(@Valid @RequestBody PostCreateRequest request) {
        return ApiResponse.success(postService.createPost(request));
    }

    @GetMapping
    public ApiResponse<List<PostListResponse>> getPosts(){
        return ApiResponse.success(postService.getPosts());
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDetailResponse> getPost(@PathVariable String id) {
        return ApiResponse.success(postService.getPostDetail(id));
    }

    @PatchMapping("/{id}")
    public ApiResponse<PostDetailResponse> updatePost(@PathVariable String id, @Valid @RequestBody PostCreateRequest request) {
        return ApiResponse.success(postService.patch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
