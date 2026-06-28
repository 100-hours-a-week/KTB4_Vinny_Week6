package com.vinny.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //공통
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력값이 유효하지 않습니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "입력값이 비었습니다."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "요청 구조가 잘못되었습니다."),

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "서명이 맞지 않는 토큰입니다."),

    //유저 실패
    LOGIN_INPUT_INVALID(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호를 확인해주세요"),
    AUTH_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_NOT_WITHDRAW_STATUS(HttpStatus.BAD_REQUEST, "탈퇴 요청 상태가 아닙니다."),
    WITHDRAW_PENDING_EXCEPTION(HttpStatus.BAD_REQUEST, "탈퇴 요청 상태로 로그인이 불가능합니다."),

    //게시글 실패
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "페이지 번호는 0보다 커야 합니다."),
    PAGE_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST, "size가 너무 큽니다."),
    INVALID_PAGE_SIZE(HttpStatus.BAD_REQUEST, "size는 0보다 커야 합니다."),
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "경로가 잘못되었습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    URL_NOT_FOUND(HttpStatus.NOT_FOUND, "경로가 올바르지 않습니다."),

    //댓글 실패
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
