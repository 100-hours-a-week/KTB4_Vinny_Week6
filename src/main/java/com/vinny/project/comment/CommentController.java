package com.vinny.project.comment;


import com.vinny.project.comment.dto.request.CommentCreateRequest;
import com.vinny.project.comment.dto.response.CommentResponse;
import com.vinny.project.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    public ApiResponse<CommentResponse> createComment(@PathVariable Long userId,@PathVariable Long postId, @Valid @RequestBody CommentCreateRequest request){
        return ApiResponse.success(commentService.createComment(userId, postId, request));
    }

    @GetMapping("/posts/{postId}/comments")
    public ApiResponse<List<CommentResponse>> getComments(@PathVariable Long postId){
        return ApiResponse.success(commentService.getComments(postId));
    }

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ApiResponse<CommentResponse> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid  @RequestBody CommentCreateRequest request){
        return ApiResponse.success(commentService.patch(commentId, request));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.delete(postId, commentId);
        return ApiResponse.success(null);
    }
}
