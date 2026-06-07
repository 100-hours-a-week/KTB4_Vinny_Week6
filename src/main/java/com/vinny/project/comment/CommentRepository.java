package com.vinny.project.comment;

import com.vinny.project.comment.exception.CommentNotFoundException;
import com.vinny.project.post.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class CommentRepository {
    private final Map<String, Comment> comments = new ConcurrentHashMap<>();

    public CommentRepository() {
        comments.put("1", new Comment("1", "1", "1","comment1111", LocalDateTime.now()));
        comments.put("2", new Comment("1", "1","2", "comment1111", LocalDateTime.now()));
        comments.put("3", new Comment("3", "1","3", "comment1111", LocalDateTime.now()));
    }

    public void save(String id, Comment comment) {
        comments.put(id, comment);
    }

    public Comment findById(String id) {
        if (comments.containsKey(id)) {
            return comments.get(id);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public List<Comment> findByPostId(String postId) {
        return comments.values().stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }

    public void delete(String id){
        comments.remove(id);
    }


}
