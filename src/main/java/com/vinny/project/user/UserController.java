package com.vinny.project.user;


import com.vinny.project.response.ApiResponse;
import com.vinny.project.user.dto.request.UserCreateRequest;
import com.vinny.project.user.dto.request.UserPatchRequest;
import com.vinny.project.user.dto.request.UserSignInRequest;
import com.vinny.project.user.dto.response.UserIdResponse;
import com.vinny.project.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<UserIdResponse>> signUp(@Valid @RequestBody UserCreateRequest request){
        UserIdResponse user = userService.addUser(request);
        return ResponseEntity.status(201).body(ApiResponse.created(user));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> signIn(@Valid @RequestBody UserSignInRequest request){
        User user = userService.signIn(request);
        return ResponseEntity.status(200).body(user);
    }

    //테스트용 전체 유저 API
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable String id){
        User user = userService.findById(id);
        return ApiResponse.ok(new UserResponse(user));
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable String id,@Valid @RequestBody UserPatchRequest request){
        userService.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.delete(id);
    }
}
