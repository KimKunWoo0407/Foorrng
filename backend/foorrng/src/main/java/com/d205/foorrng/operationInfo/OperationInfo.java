package com.d205.foorrng.operationInfo;

import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.operationInfo.dto.OperationInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;

    private String day;             // 요일

    private Long startTime;        // 시작 시간

    private Long endTime;          // 종료 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mark_seq")
    @JsonIgnore
    private Mark mark;


    public void update(OperationInfoDto operationInfoDto) {
        this.day = operationInfoDto.getOperationInfoList().get(0).get("day").toString();
        this.startTime = Long.parseLong(operationInfoDto.getOperationInfoList().get(0).get("startTime").toString());
        this.endTime = Long.parseLong(operationInfoDto.getOperationInfoList().get(0).get("endTime").toString());
    }

}
