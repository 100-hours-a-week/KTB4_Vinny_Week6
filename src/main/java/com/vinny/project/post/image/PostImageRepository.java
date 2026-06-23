package com.vinny.project.post.image;

import com.vinny.project.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    void deleteAllByPost(Post post);
}
