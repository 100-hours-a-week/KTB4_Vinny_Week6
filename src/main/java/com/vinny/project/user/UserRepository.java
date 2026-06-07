package com.vinny.project.user;

import com.vinny.project.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository(){
        users.add(new User("1", "test1@gmail.com", "1AbC!cccccc", "vinny", ""));
        users.add(new User("2", "test2@gmail.com", "1AbC!cccccc", "selina", ""));
        users.add(new User("3", "test3@gmail.com", "1AbC!cccccc", "jun", ""));
    }

    public void save(User user){
        if(existsById(user.getId()) || existsByEmail(user.getEmail()) || existsByNickName(user.getNickname())){
            // 예외 띄우기
            throw new DuplicateFormatFlagsException(user.getId() + " already exists");
        }
        users.add(user);
    }

    public User findById(String id){
        for(User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }

    public List<User> findAll(){
        return users;
    }

    public boolean existsById(String id){
        for(User user : users){
            if(user.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public boolean existsByNickName(String nickName){
        for(User user : users){
            if(user.getNickname().equals(nickName)){
                return true;
            }
        }
        return false;
    }

    public User update(String id, User newUser) {
        User user = findById(id);

        user.setNickname(newUser.getNickname());
        user.setPassword(newUser.getPassword());
        user.setProfileImageUrl(newUser.getProfileImageUrl());
        return user;
    }

    public void delete(String id){
        //users.removeIf(user -> user.getId().equals(id));
        User user = findById(id);
        users.remove(user);
    }
}
