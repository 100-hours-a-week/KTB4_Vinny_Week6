package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserUpdateProfileRequest {
    @NotBlank
    @Pattern(regexp = "^[^\\s]{1,10}$", message = "닉네임은 공백 없이 10자 이내여야 합니다.")
    private String nickname;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$")
    private String profileImageUrl;
}
