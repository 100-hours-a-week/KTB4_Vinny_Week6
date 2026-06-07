package com.vinny.project.user;

import com.vinny.project.user.dto.request.UserCreateRequest;
import com.vinny.project.user.dto.request.UserPatchRequest;
import com.vinny.project.user.dto.request.UserSignInRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserCreateRequest request){
        //비밀번호 해시처리 필요
        User user = new User(UUID.randomUUID().toString(), request.getEmail(), request.getPassword(), request.getNickname(), request.getProfileImageUrl());
        userRepository.save(user);

        return user;
    }

    public User signIn(@Valid @RequestBody UserSignInRequest request){
        for(User user : userRepository.findAll()){
            if(user.getEmail().equals(request.getEmail()) &&  user.getPassword().equals(request.getPassword())){
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id);
    }

    public void patch(String id, @RequestBody UserPatchRequest request){
        User user = userRepository.findById(id);

        if(user == null){
            return;
        }

        if(request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPassword(request.getPassword());
        }

        if(request.getNickname() != null && !request.getNickname().isEmpty()){
            user.setNickname(request.getNickname());
        }

        if(request.getProfileImageUrl() != null && !request.getProfileImageUrl().isEmpty()) {
            user.setProfileImageUrl(request.getProfileImageUrl());
        }
    }

    public void delete(String id){
        userRepository.delete(id);
    }
}
