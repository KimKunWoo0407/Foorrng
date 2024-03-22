package com.d205.foorrng.mark;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Builder
//@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    @JsonIgnore
    private Foodtrucks foodtrucks;

    @OneToMany(mappedBy = "mark", cascade = CascadeType.REMOVE)
//    @JsonIgnore
    private List<OperationInfo> operationInfoList;
}
