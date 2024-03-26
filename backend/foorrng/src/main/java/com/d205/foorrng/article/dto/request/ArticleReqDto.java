package com.d205.foorrng.article.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReqDto {
    private long userId;
    private String title;
    private String content;
    private double latitude;
    private double longitude;
    private String phone;
    private String email;
    private String kakaoId;
    private String organizer;
    private long startDate;
    private long endDate;
    private String address;
}
