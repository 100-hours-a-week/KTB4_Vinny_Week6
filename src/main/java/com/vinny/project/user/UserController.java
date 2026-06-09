package com.vinny.project.user;


import com.vinny.project.auth.token.TokenRepository;
import com.vinny.project.response.ApiResponse;
import com.vinny.project.user.dto.request.UserCreateRequest;
import com.vinny.project.user.dto.request.UserPatchPasswordRequest;
import com.vinny.project.user.dto.request.UserPatchProfileRequest;
import com.vinny.project.user.dto.request.UserSignInRequest;
import com.vinny.project.user.dto.response.SignInResponse;
import com.vinny.project.user.dto.response.UserIdResponse;
import com.vinny.project.user.dto.response.UserResponse;
import com.vinny.project.user.dto.response.UserSummary;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    UserController(UserService userService, TokenRepository tokenRepository) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<UserIdResponse>> signUp(@Valid @RequestBody UserCreateRequest request){
        return ResponseEntity.status(201).body(ApiResponse.success(userService.addUser(request)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<SignInResponse>> signIn(@Valid @RequestBody UserSignInRequest request){
        return ResponseEntity.status(200).body(ApiResponse.success(userService.signIn(request)));
    }

    @PostMapping("/logout")
    public void logout(){

    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id){
        return ApiResponse.success(new UserResponse(userService.findById(id)));
    }

    @PatchMapping("/{id}/profile")
    public ApiResponse<UserSummary> updateUserProfile(@PathVariable String id, @Valid @RequestBody UserPatchProfileRequest request){
        return ApiResponse.success(userService.patchProfile(id, request));
    }

    @PatchMapping("/{id}/password")
    public ApiResponse<Object> updateUserPassword(@PathVariable String id, @Valid @RequestBody UserPatchPasswordRequest request){
        userService.patchPassword(id, request);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("/sign-in"));
    }
}
