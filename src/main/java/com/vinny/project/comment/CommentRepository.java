package com.vinny.project.comment;

import com.vinny.project.comment.exception.CommentNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CommentRepository {
    private static final Map<String, Comment> comments = new ConcurrentHashMap<>();

    public CommentRepository() {
        for(int i =1; i<= 3; i++){
            comments.put(String.valueOf(i), new Comment("1","1",String.valueOf(i),"comment"+i, LocalDateTime.now()));
        }
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

    public boolean existById(String id) {
        return comments.containsKey(id);
    }

    public ArrayList<Comment> findAll() {
        return new ArrayList<>(comments.values());
    }

    public void delete(String id){
        comments.remove(id);
    }


}
