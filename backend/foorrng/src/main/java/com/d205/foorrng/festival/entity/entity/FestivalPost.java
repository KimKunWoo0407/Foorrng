package com.d205.foorrng.festival.entity.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Table(name = "tb_festival")
@Entity //JPA 엔터티임을나타냄
@Slf4j //log 객체를 자동으로 생성한다.
@Getter //getter 메서드를 자동으로 생성해줌,
public class FestivalPost {

    @Id //해당 필드가 엔티티의 기본 키임을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프라이빗 키
    @Column(name = "festival_seq") // 테이블의 컬럼과 매핑 시킨다.
    private Long id;

    @Column(nullable = false, length = 200)
    private String Title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime postedAt;





    //Table 생성






}
