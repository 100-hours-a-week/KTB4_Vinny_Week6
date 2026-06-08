package com.vinny.project.comment;


import com.vinny.project.comment.dto.request.CommentCreateRequest;
import com.vinny.project.comment.dto.response.CommentResponse;
import com.vinny.project.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ApiResponse<CommentResponse> createComment(@PathVariable String postId, @Valid @RequestBody CommentCreateRequest request){
        return ApiResponse.success(commentService.createComment(postId, request));
    }

    @GetMapping
    public ApiResponse<List<CommentResponse>> getComments(@PathVariable String postId){
        return ApiResponse.success(commentService.getComments(postId));
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<CommentResponse> updateComment(@PathVariable String postId, @PathVariable String commentId,@Valid  @RequestBody CommentCreateRequest request){
        return ApiResponse.success(commentService.patch(postId, commentId, request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String postId, @PathVariable String commentId){
        commentService.delete(postId, commentId);
        return ResponseEntity.noContent().build();
    }




}
