package com.d205.foorrng.foodtruck.request;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private Long price;
    @NotEmpty
    private String picture;
    private Foodtrucks foodtrucks;
}
