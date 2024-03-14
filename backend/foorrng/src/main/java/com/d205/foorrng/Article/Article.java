package com.d205.foorrng.Article;

import com.d205.foorrng.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_seq")
    private Long id;

    private String title;

    private String content;

    private Long created_datetime;

    private Long updeated_datetime;

    private Double latitude;            // 위도

    private Double longtitude;          // 경도

    private String phone;               // 작성자 전화번호

    private String email;               // 작성자 이메일

    private String kakaoID;             // 작성자 카카오 id

    private String organizer;           // 주최측

    private Long start_date;            // 시작일

    private Long end_date;              // 종료일

    private String address;             // 지번 주소

    private String picture;             // 행사 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

}
