package com.d205.foorrng.Foodtrucks.entity;

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
public class OperationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_seq")
    private Long id;

    private String day;             // 요일

    private Long start_time;        // 시작 시간

    private Long end_time;          // 종료 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mark_seq")
    private Mark mark;

}
