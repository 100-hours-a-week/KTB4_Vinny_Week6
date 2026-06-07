package com.vinny.project.comment;

import com.vinny.project.comment.dto.request.CommentCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(String postId, CommentCreateRequest request) {
        String id = UUID.randomUUID().toString();
        Comment comment = new Comment("1", postId, id, request.getContent(), LocalDateTime.now());
        commentRepository.save(id, comment);
        return comment;
    }

    public List<Comment> getComments(String postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment findById(String commentId) {
        return commentRepository.findById(commentId);
    }

    public void patch(String commentId, @RequestBody CommentCreateRequest request) {
        Comment comment = findById(commentId);

        if(comment == null) {
            return;
        }

        if(request.getContent() != null || !request.getContent().isEmpty()) {
            comment.setContent(request.getContent());
        }
    }

    public void delete(String commentId) {
        commentRepository.delete(commentId);
    }
}
