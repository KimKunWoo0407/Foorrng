package com.d205.foorrng.festival;

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
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_seq")
    private Long id;

    private String festivalName;       // 축제 이름

    private Long startDay;             // 축제 시작 날짜

    private Long finishDay;            // 축제 종료 날짜

    private String country;             // 축제 지역
}
