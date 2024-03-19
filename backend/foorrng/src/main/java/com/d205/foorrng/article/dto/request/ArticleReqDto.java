package com.d205.foorrng.article.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReqDto {
    private String title;
    private String content;
//    private long create_datetime;
//    private long updated_datetime;
    private double latitude;
    private double longitude;

    private String phone;

    private String email;

    private String kakaoId;

    private String organizer;

    private long start_date;

    private long end_date;

    private String address;

}
