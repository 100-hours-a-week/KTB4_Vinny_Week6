package com.vinny.project.post.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostUpdateRequest {
    @Size(min = 1, max = 26, message = "최대 26자까지 가능합니다.")
    private String title;

    private String content;

    @NotEmpty(message = "이미지는 필수입니다.")
    @Size(min = 1, max = 5, message = "이미지는 최소 1개에서 최대 5개까지만 업로드할 수 있습니다.")
    private List<MultipartFile> images;
}
