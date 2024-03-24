package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;

public interface FoodtrucksService {
    void foodtrucklist(FoodtrucksReqDto foodtrucksReqDto);
    void foodtruckdetail();
    void myfoodtruck();
    void myfoodtruckOper();

}
