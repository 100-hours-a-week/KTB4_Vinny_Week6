package com.vinny.project.post;

import com.vinny.project.post.exception.PostNotFoundException;
import com.vinny.project.user.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private final Map<String, Post> posts = new  ConcurrentHashMap<>();

    public PostRepository() {
        posts.put("1", new Post("1","1", "title1", "content1", "1", LocalDateTime.now(),1,1,1));
        posts.put("2", new Post("1","2", "title2", "content2", "2", LocalDateTime.now(),2,2,2));
        posts.put("3", new Post("2","3", "title3", "content3", "3", LocalDateTime.now(),3,3,3));
    }

    public void save(String id, Post post) {
        posts.put(id, post);
    }

    public Post findById(String id) {
        if(posts.containsKey(id)){
            return posts.get(id);
        } else {
            throw new PostNotFoundException(id);
        }
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public void delete(String id) {
        Post post = findById(id);
        posts.remove(id, post);
    }
}
