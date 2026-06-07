package com.vinny.project.comment;


import com.vinny.project.comment.dto.request.CommentCreateRequest;
import com.vinny.project.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentRepository commentRepository, CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@PathVariable String postId, @RequestBody CommentCreateRequest request){
        return commentService.createComment(postId, request);
    }

    @GetMapping
    public ApiResponse<ArrayList<Comment>> getComments(@PathVariable String postId){
        return ApiResponse.ok(new ArrayList<>(commentService.getComments(postId)));
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable String commentId, @RequestBody CommentCreateRequest request){
        commentService.patch(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable String commentId){
        commentService.delete(commentId);
    }




}
