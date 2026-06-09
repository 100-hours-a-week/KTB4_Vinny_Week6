package com.vinny.project.post;

import com.vinny.project.post.dto.request.PostCreateRequest;
import com.vinny.project.post.dto.response.PostDetailResponse;
import com.vinny.project.post.dto.response.PostListResponse;
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
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createPost(PostCreateRequest request) {
        String id = UUID.randomUUID().toString();
        Post post = new Post("1", id, request.getTitle(), request.getContent(), request.getPostImageUrl(), LocalDateTime.now(), 0, 0, 0);
        postRepository.save(id, post);
        return post;
    }

    public List<PostListResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToPostListResponse)
                .toList();
    }

    public PostDetailResponse getPostDetail(String id) {
        Post post = postRepository.findById(id);
        return convertToPostDetailResponse(post);
    }

    private PostListResponse convertToPostListResponse(Post post) {
        User writer = userService.findById(post.getWriterId());
        UserSummary writerSummary = new UserSummary(writer.getNickname(), writer.getProfileImageUrl());
        return new PostListResponse(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getViewCount(),
                writerSummary
        );
    }

    private PostDetailResponse convertToPostDetailResponse(Post post) {
        User writer = userService.findById(post.getWriterId());
        UserSummary writerSummary = new UserSummary(writer.getNickname(), writer.getProfileImageUrl());
        return new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getViewCount(),
                writerSummary,
                post.getContent()
        );
    }

    public PostDetailResponse patch(String id, @RequestBody PostCreateRequest request) {
        Post post = postRepository.findById(id);
        User user = userService.findById(post.getWriterId());
        UserSummary writerSummary = new UserSummary(user.getNickname(), user.getProfileImageUrl());

        if (post == null) {
            throw new PostNotFoundException();
        }
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setPostImageUrl(request.getPostImageUrl());

        return new PostDetailResponse(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getLikeCount(),
                post.getCommentCount(),
                post.getViewCount(),
                writerSummary,
                post.getContent()
        );
    }

    public void delete(String id) {
        postRepository.delete(id);
    }
}
