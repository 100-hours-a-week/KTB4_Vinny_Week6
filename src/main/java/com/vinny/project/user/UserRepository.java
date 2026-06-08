package com.vinny.project.user;

import com.vinny.project.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private static Map<String, User> users = new ConcurrentHashMap<>();

    public UserRepository(){
        users.put("1", new User("1", "test1@gmail.com", "1", "vinny", ""));
        users.put("2",new User("2", "test2@gmail.com", "2", "selina", ""));
        users.put("3",new User("3", "test3@gmail.com", "3", "jun", ""));
        users.put("4",new User("4", "test4@gmail.com", "4", "huey", ""));
        users.put("5",new User("5", "test5@gmail.com", "5", "keryn", ""));
        users.put("6",new User("6", "test6@gmail.com", "6", "justin", ""));
    }

    public void saveUser(User user){
        users.put(user.getId(), user);
    }

    public User findById(String id){
        if(existsById(id)){
           return users.get(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }

    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    public boolean existsById(String id){
        return users.containsKey(id);
    }

    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean existsByNickname(String nickname){
        return users.values().stream()
                .anyMatch(user -> user.getNickname().equals(nickname));
    }

    public void delete(String id){
        users.remove(findById(id));
    }
}
