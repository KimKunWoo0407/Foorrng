package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService{

    private final FoodtruckRepository foodtruckRepository;

    @Override
    @Transactional
    public void createFoodtrucks(){
        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        foodtruckRepository.save(foodtrucks);
    }

    @Override
    @Transactional
    public void createFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto){
        Foodtruck foodtruck = Foodtruck.builder()
                .name(foodtruckCreateDto.getName())
                .announcement(foodtruckCreateDto.getAnnouncement())
                .picture(foodtruckCreateDto.getPicture())
                .accountInfo(foodtruckCreateDto.getAccountInfo())
                .carNumber(foodtruckCreateDto.getCarNumber())
                .phoneNumber(foodtruckCreateDto.getPhoneNumber())
                .build();
        foodtruckRepository.save(foodtruck);
    };

    @Override
    @Transactional
    public List<FoodtruckResDto> foodtrucks(){};

    @Override
    @Transactional
    public void updateFoodtruck(){};

    @Override
    @Transactional
    public Long deleteFoodtruck(){};



}
