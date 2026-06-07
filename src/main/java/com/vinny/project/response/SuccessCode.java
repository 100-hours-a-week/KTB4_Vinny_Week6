package com.vinny.project.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode {
    CREATE_USER_SUCCESS(HttpStatus.CREATED, "유저 생성에 성공했습니다."),
    SIGN_IN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
    GET_USER_SUCCESS(HttpStatus.OK, "유저 정보 호출에 성공했습니다."),
    UPDATE_USER_SUCCESS(HttpStatus.OK, "유저 정보 수정에 성공했습니다."),
    DELETE_USER_SUCCESS(HttpStatus.OK, "유저 정보 삭제에 성공했습니다."),

    //게시글 성공
    CREATE_POST_SUCCESS(HttpStatus.CREATED, "게시글 작성에 성공했습니다."),
    GET_POSTS_SUCCESS(HttpStatus.OK, "게시글 목록 호출에 성공했습니다."),
    GET_PAGINATION_POSTS_SUCCESS(HttpStatus.OK, "게시글 목록 페이지네이션 호출에 성공했습니다."),
    GET_INFINITE_SCROLL_POSTS_SUCCESS(HttpStatus.OK, "게시글 무한스크롤 호출에 성공했습니다."),
    GET_POST_DETAIL_SUCCESS(HttpStatus.OK, "게시글 상세 호출에 성공했습니다."),

    //댓글 성공
    GET_COMMENTS_SUCCESS(HttpStatus.OK, "댓글 목록 호출에 성공했습니다.");


    private final HttpStatus status;
    private final String message;

}
