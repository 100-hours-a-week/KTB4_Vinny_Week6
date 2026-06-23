package com.vinny.project.comment;

import com.vinny.project.comment.dto.request.CommentCreateRequest;
import com.vinny.project.comment.dto.response.CommentResponse;
import com.vinny.project.comment.exception.CommentNotFoundException;
import com.vinny.project.enums.CommentStatus;
import com.vinny.project.post.Post;
import com.vinny.project.post.PostService;
import com.vinny.project.user.User;
import com.vinny.project.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    public void updateCommentCount(Long postId) {
        Post post =  postService.findById(postId);
        int totalCommentCount = commentRepository.countByPostPostId(postId);
        post.updateCommentCount(totalCommentCount);
    }

    @Transactional
    public CommentResponse createComment(Long userId, Long postId, CommentCreateRequest request) {
        User user = userService.findById(userId);
        Post post = postService.findById(postId);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .status(CommentStatus.ACTIVE)
                .author(user)
                .post(post)
                .parentComment(null)
                .build();

        commentRepository.save(comment);

        updateCommentCount(postId);
        return CommentResponse.from(comment);
    }

    public List<CommentResponse> getComments(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostPostId(postId);
        return  comments.stream().map(CommentResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse patch(Long commentId, CommentCreateRequest request) {
        Comment comment = findById(commentId);

        comment.changeContent(request.getContent());
        return CommentResponse.from(comment);
    }

    @Transactional
    public void delete(Long postId, Long commentId) {
        Comment comment = findById(commentId);

        comment.deleteComment();
        updateCommentCount(postId);
    }
}
