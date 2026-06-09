package com.vinny.project.user;

import com.vinny.project.auth.token.TokenRepository;
import com.vinny.project.exception.BusinessException;
import com.vinny.project.exception.ErrorCode;
import com.vinny.project.user.dto.request.UserCreateRequest;
import com.vinny.project.user.dto.request.UserPatchPasswordRequest;
import com.vinny.project.user.dto.request.UserPatchProfileRequest;
import com.vinny.project.user.dto.request.UserSignInRequest;
import com.vinny.project.user.dto.response.SignInResponse;
import com.vinny.project.user.dto.response.UserIdResponse;
import com.vinny.project.user.dto.response.UserSummary;
import com.vinny.project.user.exception.AuthPasswordMismatchException;
import com.vinny.project.user.exception.DuplicateEmailException;
import com.vinny.project.user.exception.DuplicateNicknameException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
    }

    public UserIdResponse addUser(UserCreateRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateEmailException();
        } else if(userRepository.existsByNickname(request.getNickname())){
            throw new DuplicateNicknameException();
        } else if(!request.getPassword().equals(request.getPasswordCheck())){
            throw new AuthPasswordMismatchException();
        }

        String userId = UUID.randomUUID().toString();
        User user = new User(userId, request.getEmail(), request.getPassword(), request.getNickname(), request.getProfileImageUrl());
        userRepository.saveUser(user);

        return new UserIdResponse(user);
    }

    public SignInResponse signIn(@Valid @RequestBody UserSignInRequest request){
        User user = authenticate(request.getEmail(), request.getPassword());
        String token = UUID.randomUUID().toString();
        return new SignInResponse(token, user.getId());
    }

    public User authenticate(String email, String password){
        User user = userRepository.findByEmail(email);
        if(!user.getPassword().equals(password)){
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
        return user;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User findById(String id){
        return userRepository.findById(id);
    }

    public UserSummary patchProfile(String id, @RequestBody UserPatchProfileRequest request){
        User user = findById(id);
        if(userRepository.existsByNickname(request.getNickname())){
            throw new DuplicateNicknameException();
        }
        user.setNickname(request.getNickname());
        user.setProfileImageUrl(request.getProfileImageUrl());

        return new UserSummary(user.getNickname(),user.getProfileImageUrl());
    }

    public void patchPassword(String id, @RequestBody UserPatchPasswordRequest request){
        User user = findById(id);
        if(!request.getPassword().equals(request.getPasswordCheck())){
            throw new AuthPasswordMismatchException();
        }
        user.setPassword(request.getPassword());
    }

    public void delete(String id){
        userRepository.delete(id);
    }
}
