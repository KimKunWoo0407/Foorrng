package com.d205.foorrng.article.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
public class ArticleResDto {
    private long articleId;
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
    public static ArticleResDto of(long articleId, long userId, String title,
                                   String content, double latitude, double longitude,
                                   String phone, String email, String kakaoId,
                                   String organizer, long startDate, long endDate, String address){
        return builder().articleId(articleId).userId(userId).title(title)
                .content(content).latitude(latitude).longitude(longitude)
                .phone(phone).email(email).kakaoId(kakaoId)
                .organizer(organizer).startDate(startDate).endDate(endDate).address(address)
                .build();
    }

    @Builder
    public ArticleResDto(long articleId, long userId, String title, String content, double latitude, double longitude, String phone, String email, String kakaoId, String organizer, long startDate, long endDate, String address) {
        this.articleId = articleId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.email = email;
        this.kakaoId = kakaoId;
        this.organizer = organizer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
    }
}
