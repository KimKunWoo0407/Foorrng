package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FoodtrucksResDto {
    private Long id;
    private FoodtruckRole foodtruckRole;
    private List<FoodtruckResDto> foodtrucks;
    private List<FoodtruckRepResDto> foodtruckReports;

    // 주 생성자
    public FoodtrucksResDto(Long id, FoodtruckRole foodtruckRole, List<FoodtruckResDto> foodtrucks, List<FoodtruckRepResDto> foodtruckReports) {
        this.id = id;
        this.foodtruckRole = foodtruckRole;
        this.foodtrucks = foodtrucks;
        this.foodtruckReports = foodtruckReports;
    }

    // Foodtrucks 엔티티를 기반으로 FoodtrucksResDto 인스턴스를 생성하는 정적 팩토리 메서드
//    public static FoodtrucksResDto fromEntity(Foodtrucks foodtrucks, List<FoodtruckResDto> foodtruckDtos, List<FoodtruckRepResDto> foodtruckReportDtos) {
//        return new FoodtrucksResDto(
//                foodtrucks.getId(),
//                foodtrucks.getFoodtruckRole(),
//                foodtruckDtos,
//                foodtruckReportDtos
//        );
//    }
}
