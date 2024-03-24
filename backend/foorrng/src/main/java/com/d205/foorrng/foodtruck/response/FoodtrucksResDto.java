package com.d205.foorrng.foodtruck.response;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
public class FoodtrucksResDto {
    private final Long foodtruckId;
    private final Long markId;
    private final Double latitude;
    private final Double longitutde;
    private final String name;
    private final String img;
    private final String type;
    private final List<String> category;
    private final int reviewCnt;
    private final Boolean like;
    private final Boolean operation;

    public FoodtrucksResDto(Long foodtruckId, Long markId, Double latitude, Double longitutde, String name, String img, String type, List<String> category, int reviewCnt, Boolean like, Boolean operation){
        this.foodtruckId = foodtruckId;
        this.markId = markId;
        this.latitude = latitude;
        this.longitutde = longitutde;
        this.name = name;
        this.img = img;
        this.type = type;
        this.category = category;
        this.reviewCnt = reviewCnt;
        this.like = like;
        this.operation = operation;

    }

}
