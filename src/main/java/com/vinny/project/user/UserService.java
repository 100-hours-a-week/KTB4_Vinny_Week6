package com.vinny.project.user;

import com.vinny.project.enums.UserStatus;
import com.vinny.project.enums.WithdrawReasonType;

import com.vinny.project.user.dto.request.*;
import com.vinny.project.user.dto.response.SignInResponse;
import com.vinny.project.user.dto.response.UserIdResponse;
import com.vinny.project.user.dto.response.AuthorSummary;
import com.vinny.project.user.dto.response.UserResponse;
import com.vinny.project.user.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private static final String DEFAULT_PROFILE_IMAGE_URL = "https://github.com/user-attachments/assets/1668d816-f8d3-483f-80aa-3b960176c557";

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User getReferenceById(Long userId) {
        return userRepository.getReferenceById(userId);
    }

    @Transactional
    public UserIdResponse addUser(UserCreateRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateEmailException();
        } else if(userRepository.existsByNickname(request.getNickname())){
            throw new DuplicateNicknameException();
        } else if(!request.getPassword().equals(request.getPasswordConfirm())){
            throw new AuthPasswordMismatchException();
        }

        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPasswordConfirm())
                .status(UserStatus.ACTIVE)
                .build();
        if(request.getProfileImageUrl() == null || request.getProfileImageUrl().isEmpty()){
            user.changeProfileImageUrl(DEFAULT_PROFILE_IMAGE_URL);
        }

        User savedUser = userRepository.save(user);

        return new UserIdResponse(savedUser.getUserId());
    }

    @Transactional
    public SignInResponse signIn( UserSignInRequest request){
        User user = authenticate(request.getEmail(), request.getPassword());
        String token = UUID.randomUUID().toString();
        return new SignInResponse(token, user.getUserId());
    }

    @Transactional
    public User authenticate(String email, String password){
        User user = userRepository.findByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            throw new LoginInputInvalidException();
        }
        user.updateLastLoginAt();
        return user;
    }

    public UserResponse getUser(Long userId) {
        User user = findById(userId);
        return UserResponse.from(user);
    }

    @Transactional
    public AuthorSummary patchProfile(Long userId, UserUpdateProfileRequest request){
        User user = findById(userId);

        if (!user.getNickname().equals(request.getNickname()) && userRepository.existsByNickname(request.getNickname())) {
            throw new DuplicateNicknameException();
        }
        if(request.getProfileImageUrl() == null || request.getProfileImageUrl().isEmpty()){
            user.changeProfileImageUrl(UserService.DEFAULT_PROFILE_IMAGE_URL);
        }
        user.changeNickname(request.getNickname());
        return AuthorSummary.from(user);
    }

    @Transactional
    public void patchPassword(Long userId, UserUpdatePasswordRequest request){
        User user = findById(userId);
        if(!request.getPassword().equals(request.getPasswordConfirm())){
            throw new AuthPasswordMismatchException();
        }

        user.changePassword(request.getPassword());
    }

    @Transactional
    public void requestWithdraw(Long id, UserWithdrawRequest request){
        User user = findById(id);
        WithdrawReasonType type = WithdrawReasonType.ofCode(request.getWithdrawReasonType());
        user.withdrawUser(type, request.getWithdrawReasonDetail());
    }

    @Transactional
    public void cancelWithdraw(Long id){
        User user = findById(id);
        if(user.getStatus() != UserStatus.WITHDRAW_PENDING){
            throw new UserNotWithdrawException();
        }
        user.cancelWithdrawUser();
    }
}
