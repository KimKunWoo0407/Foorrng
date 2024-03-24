package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodtrucksServiceImlp implements FoodtrucksService{
    @Override
    @Transactional
    public void foodtrucklist(FoodtrucksReqDto foodtrucksReqDto){

    };
    @Override
    @Transactional
    public void foodtruckdetail(){};
    @Override
    @Transactional
    public void myfoodtruck(){};
    @Override
    @Transactional
    public void myfoodtruckOper(){};

}
