package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import com.d205.foorrng.foodtruck.response.FoodtrucksResDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FoodtrucksService {
    List<FoodtrucksResDto> foodtrucklist(FoodtrucksReqDto foodtrucksReqDto);
    Map<String, Object> foodtruckdetail(Long foodtrucksId, Long markId) throws IOException;
    Map<String, Object> myfoodtruck(Long foodtruckId);
    List<Map<String, Object>> myfoodtruckOper(Long foodtruckId);

}
