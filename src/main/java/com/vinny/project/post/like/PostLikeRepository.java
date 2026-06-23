package com.vinny.project.post.like;

import com.vinny.project.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByUserUserIdAndPostPostId(Long userId, Long postId);

    void deleteByUserUserIdAndPostPostId(Long userId, Long postId);

    int countByPostPostId(Long postId);

    void deleteAllByPost(Post post);

}
