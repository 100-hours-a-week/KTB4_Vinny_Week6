package com.vinny.project.post;

import com.oracle.svm.core.annotate.Delete;
import com.vinny.project.post.dto.request.PostCreateRequest;
import com.vinny.project.post.dto.response.PostDetailResponse;
import com.vinny.project.post.dto.response.PostListResponse;
import com.vinny.project.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest request) {
        return postService.createPost(request);
    }

    @GetMapping
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDetailResponse> getPost(@PathVariable String id) {
        Post post = postService.findById(id);
        return ApiResponse.ok(new PostDetailResponse(post));
    }

    @PatchMapping("/{id}")
    public void updatePost(@PathVariable String id, @RequestBody PostCreateRequest request) {
        postService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postService.delete(id);
    }





}
