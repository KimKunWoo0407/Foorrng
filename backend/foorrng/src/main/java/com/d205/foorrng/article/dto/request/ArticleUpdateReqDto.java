package com.d205.foorrng.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // 유효성
public class ArticleUpdateReqDto {
    @NotBlank
    private long articleId;
    private long userId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private double latitude;
    @NotBlank
    private double longitude;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String kakaoId;
    @NotBlank
    private String organizer;
    @NotBlank
    private long startDate;
    @NotBlank
    private long endDate;
    @NotBlank
    private String address;
}
