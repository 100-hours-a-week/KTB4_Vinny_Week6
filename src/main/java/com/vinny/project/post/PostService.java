package com.vinny.project.post;

import com.vinny.project.post.dto.request.PostCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostCreateRequest request) {
        String id = UUID.randomUUID().toString();
        Post post = new Post(id,UUID.randomUUID().toString(), request.getTitle(),request.getContent(), request.getPostImageUrl(), LocalDateTime.now(), 0, 0, 0);
        postRepository.save("1", post);
        return post;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id);
    }

    public void patch(String id, @RequestBody PostCreateRequest request) {
        Post post = findById(id);

        if(post == null){
            return;
        }

        if(request.getTitle() != null){
            post.setTitle(request.getTitle());
        }

        if(request.getContent() != null){
            post.setContent(request.getContent());
        }

        if(request.getPostImageUrl() != null){
            post.setPostImageUrl(request.getPostImageUrl());
        }
    }

    public void delete(String id){
        postRepository.delete(id);
    }
}
