package com.d205.foorrng.operationInfo;

import com.d205.foorrng.mark.Mark;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Builder
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OperationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_seq")
    private Long id;

    private String day;             // 요일

    private Long startTime;        // 시작 시간

    private Long endTime;          // 종료 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mark_seq")
    private Mark mark;

}
