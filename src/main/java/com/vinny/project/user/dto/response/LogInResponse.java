package com.vinny.project.user.dto.response;

import java.util.UUID;

public class LogInResponse {
    private String token;

    public String getToken() {
        // 랜덤 번호 유틸로 만들기
        return UUID.randomUUID().toString();
    }
}
