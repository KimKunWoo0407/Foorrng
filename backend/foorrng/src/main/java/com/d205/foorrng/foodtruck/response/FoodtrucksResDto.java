package com.d205.foorrng.foodtruck.response;

import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodtrucksResDto {
    private Long id;
    private FoodtruckRole foodtruckRole;
    private Foodtrucks foodtruck; // 필드 유지
    private List<FoodtruckResDto> foodtrucks;
    private List<FoodtruckRepResDto> foodtruckReports;

    // 수정된 주 생성자
//    public FoodtrucksResDto(Long id, FoodtruckRole foodtruckRole, Foodtrucks foodtruck, List<FoodtruckResDto> foodtrucks, List<FoodtruckRepResDto> foodtruckReports) {
//        this.id = id;
//        this.foodtruckRole = foodtruckRole;
//        this.foodtruck = foodtruck; // 필드 초기화
//        this.foodtrucks = foodtrucks;
//        this.foodtruckReports = foodtruckReports;
//    }

    // 수정된 fromEntity 정적 팩토리 메서드
    public static FoodtrucksResDto fromEntity(Foodtrucks foodtrucks, List<FoodtruckResDto> foodtruckDtos, List<FoodtruckRepResDto> foodtruckReportDtos) {
        return new FoodtrucksResDto(
                foodtrucks.getId(),
                foodtrucks.getFoodtruckRole(),
                foodtrucks,
                foodtruckDtos,
                foodtruckReportDtos
        );
    }
}