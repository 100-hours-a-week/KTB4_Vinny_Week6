package com.vinny.project.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPatchProfileRequest {
    @NotBlank
    private String nickname;

    @Pattern(regexp = "^$|^(default\\.png|(https?://).*\\.(jpg|jpeg|png|gif|webp))$")
    private String profileImageUrl;
}
