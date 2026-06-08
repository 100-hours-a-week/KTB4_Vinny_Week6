package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserCreateRequest {
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "비밀번호 조건이 충족되지 않았습니다.")
    private String password;

    @NotBlank
    private String nickname;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$", message = "이미지는 default.png 또는 URL 형식이어야 합니다.")
    private String profileImageUrl;
}
