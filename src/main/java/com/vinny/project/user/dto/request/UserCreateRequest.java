package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "올바른 이메일 주소 형식을 입력해주세요.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "비밀번호는 8자 이상, 20자 이하이며, 대문자, 소문자, 숫자, 특수문자를 각각 최소 1개 포함해야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "비밀번호가 다릅니다.")
    private String passwordCheck;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9가-힣_-]{2,10}$", message = "닉네임은 공백 없이 10자 이내여야 합니다.")
    private String nickname;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$", message = "이미지는 URL 형식이어야 합니다.")
    private String profileImageUrl;
}
