package com.vinny.project.comment;

import com.vinny.project.comment.dto.request.CommentCreateRequest;
import com.vinny.project.comment.dto.response.CommentResponse;
import com.vinny.project.comment.exception.CommentNotFoundException;
import com.vinny.project.post.PostRepository;
import com.vinny.project.post.exception.PostNotFoundException;
import com.vinny.project.user.User;
import com.vinny.project.user.UserService;
import com.vinny.project.user.dto.response.UserSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public CommentResponse createComment(String postId, CommentCreateRequest request) {
        if(!postRepository.existsById(postId)){
            throw new PostNotFoundException();
        }
        String id = UUID.randomUUID().toString();
        Comment comment = new Comment("1", postId, id, request.getContent(), LocalDateTime.now());
        commentRepository.save(id, comment);

        User writer = userService.findById(comment.getWriterId());
        UserSummary writerSummary = new UserSummary(writer.getNickname(), writer.getProfileImageUrl());

        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getCreatedAt(),
                writerSummary
        );
    }

    public List<CommentResponse> getComments(String postId) {
        if(!postRepository.existsById(postId)){
            throw new PostNotFoundException();
        }
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .map(this::convertToCommentResponse)
                .toList();
    }

    private CommentResponse convertToCommentResponse(Comment comment) {
        User writer = userService.findById(comment.getWriterId());
        UserSummary writerSummary = new UserSummary(writer.getNickname(), writer.getProfileImageUrl());
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getCreatedAt(),
                writerSummary
        );
    }


    public Comment findById(String commentId) {
        return commentRepository.findById(commentId);
    }

    public CommentResponse patch(String postId, String commentId, @RequestBody CommentCreateRequest request) {
        if(!postRepository.existsById(postId)){
            throw new PostNotFoundException();
        }
        Comment comment = findById(commentId);
        User writer = userService.findById(comment.getWriterId());
        UserSummary writerSummary = new UserSummary(writer.getNickname(), writer.getProfileImageUrl());

        if(comment == null) {
            throw new CommentNotFoundException();
        }
        comment.setContent(request.getContent());
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getCreatedAt(),
                writerSummary
        );
    }

    public void delete(String postId, String commentId) {
        if(!postRepository.existsById(postId)){
            throw new PostNotFoundException();
        }

        if(!commentRepository.existById(commentId)){
            throw new CommentNotFoundException();
        }

        commentRepository.delete(commentId);
    }
}
