package com.vinny.project.user;

import com.vinny.project.response.ApiResponse;
import com.vinny.project.user.dto.request.*;
import com.vinny.project.user.dto.response.UserSignInResponse;
import com.vinny.project.user.dto.response.UserIdResponse;
import com.vinny.project.user.dto.response.UserResponse;
import com.vinny.project.user.dto.response.AuthorSummary;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<UserIdResponse>> signUp(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity.status(201).body(ApiResponse.success(userService.addUser(request)));
    }

    @PostMapping("/sign-in")
    public ApiResponse<UserSignInResponse> signIn(@Valid @RequestBody UserSignInRequest request){
        return ApiResponse.success(userService.signIn(request));
    }

    @PostMapping("/sign-out")
    public ApiResponse<String> signOut(){
        return ApiResponse.success("/sign-in");
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long userId){
        return ApiResponse.success(userService.getUser(userId));
    }

    @PatchMapping("/{userId}/profile")
    public ApiResponse<AuthorSummary> updateUserProfile(@PathVariable Long userId, @Valid @RequestBody UserUpdateProfileRequest request){
        return ApiResponse.success(userService.updateProfile(userId, request));
    }

    @PatchMapping("/{userId}/password")
    public ApiResponse<Void> updateUserPassword(@PathVariable Long userId, @Valid @RequestBody UserUpdatePasswordRequest request){
        userService.updatePassword(userId, request);
        return ApiResponse.success(null);
    }

    @PatchMapping("/{userId}/withdraw")
    public ApiResponse<String> requestWithdraw(
            @PathVariable Long userId,
            @Valid @RequestBody UserWithdrawRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ApiResponse.fail("요청 데이터가 올바르지 않습니다" + errorMessage);
        }

        userService.requestWithdraw(userId, request);
        return ApiResponse.success("탈퇴 요청이 완료되었습니다.");
    }

    @PatchMapping("/{userId}/withdraw-cancel")
    public ApiResponse<String> cancelWithdraw(@PathVariable Long userId) {
        userService.cancelWithdraw(userId);
        return ApiResponse.success("탈퇴 요청이 취소되었습니다. 복귀를 환영합니다.");
    }
}
