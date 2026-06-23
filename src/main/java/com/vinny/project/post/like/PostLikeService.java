package com.vinny.project.post.like;

import com.vinny.project.post.Post;
import com.vinny.project.post.PostService;
import com.vinny.project.user.User;
import com.vinny.project.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostLikeService {

    private final UserService userService;
    private final PostService postService;
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostLikeRepository postLikeRepository, UserService userService, PostService postService) {
        this.postLikeRepository = postLikeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Transactional
    public PostLikeResponse toggleLike(Long userId, Long postId) {
        boolean alreadyLiked = postLikeRepository.existsByUserUserIdAndPostPostId(userId, postId);

        Post post = postService.findById(postId);

        if (alreadyLiked) {
            postLikeRepository.deleteByUserUserIdAndPostPostId(userId, postId);
        } else {
            User user = userService.getReferenceById(userId);
            Post postRef = postService.getReferenceById(postId);

            PostLike postLike = new PostLike(user, postRef);
            postLikeRepository.save(postLike);
        }

        int totalLikeCount = postLikeRepository.countByPostPostId(postId);
        post.updateLikeCount(totalLikeCount);

        return new PostLikeResponse(!alreadyLiked);
    }
}