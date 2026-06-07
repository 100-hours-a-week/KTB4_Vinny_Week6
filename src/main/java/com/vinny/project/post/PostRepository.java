package com.vinny.project.post;

import com.vinny.project.post.exception.PostNotFoundException;
import com.vinny.project.user.User;
import org.springframework.cglib.core.Local;
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
        for(int i = 1; i <= 10; i++) {
            posts.put(String.valueOf(i), new Post("1", String.valueOf(i), "title"+String.valueOf(i), "content"+String.valueOf(i),"", LocalDateTime.now(), i, i, i));
        }
    }

    public void save(String id, Post post) {
        posts.put(id, post);
    }

    public Post findById(String id) {
        if(posts.containsKey(id)){
            return posts.get(id);
        } else {
            throw new PostNotFoundException();
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
