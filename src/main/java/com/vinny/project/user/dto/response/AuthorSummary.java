package com.vinny.project.user.dto.response;

import com.vinny.project.user.User;
import com.vinny.project.user.exception.UserNotFoundException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorSummary {
    private String nickname;
    private String profileImageUrl;

    @Builder
    private AuthorSummary(String nickname, String profileImageUrl){
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public static AuthorSummary from(User user) {

        return AuthorSummary.builder()
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
