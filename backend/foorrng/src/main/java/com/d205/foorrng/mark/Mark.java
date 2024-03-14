package com.d205.foorrng.mark;

import com.d205.foorrng.foodtrucks.entity.Foodtrucks;
import com.d205.foorrng.operationInfo.OperationInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_seq")
    private Long id;

    private Double latitude;        // 위도

    private Double longitude;       // 경도

    private String address;         // 지번 주소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foodtrucks_seq")
    private Foodtrucks foodtrucks;

    @OneToMany(mappedBy = "mark")
    private List<OperationInfo> operationInfoList;
}
