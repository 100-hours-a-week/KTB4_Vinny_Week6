package com.vinny.project.user;

import com.vinny.project.user.exception.DuplicateNickname;
import com.vinny.project.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public UserRepository(){
        users.put("1", new User("1", "test1@gmail.com", "1AbC!cccccc", "vinny", ""));
        users.put("2",new User("2", "test2@gmail.com", "1AbC!cccccc", "selina", ""));
        users.put("3",new User("3", "test3@gmail.com", "1AbC!cccccc", "jun", ""));
    }

    public void save(String userId, User user){
        users.put(user.getId(), user);
    }

    public User findById(String id){
        if(existsById(id)){
           return users.get(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    public boolean existsById(String id){
        if(users.containsKey(id)){
            return true;
        } else {
            return false;
        }
    }

    public boolean existsByEmail(String email) {
        if(users.containsKey(email)){
            return true;
        } else {
            return false;
        }
    }

    public boolean existsByNickname(String nickname){
        if(users.containsKey(nickname)){
            return true;
        } else {
            return false;
        }
    }

    public void delete(String id){
        //users.removeIf(user -> user.getId().equals(id));
        User user = findById(id);
        users.remove(user);
    }
}
