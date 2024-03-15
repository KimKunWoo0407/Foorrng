package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
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
    private final FoodtrucksRepository foodtrucksRepository;

    @Override
    @Transactional
    public void createFoodtrucks(){

    }

    @Override
    @Transactional
    public void createFoodtruck(FoodtruckCreateDto foodtruckCreateDto){

        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        Long createdFoodTruck = foodtrucksRepository.save(foodtrucks).getId();


        Foodtruck foodtruck = Foodtruck.builder()
                .foodtrucks(foodtrucksRepository.findById(createdFoodTruck).get())
                .name(foodtruckCreateDto.getName())
                .announcement(foodtruckCreateDto.getAnnouncement())
                .picture(foodtruckCreateDto.getPicture())
                .accountInfo(foodtruckCreateDto.getAccountInfo())
                .carNumber(foodtruckCreateDto.getCarNumber())
                .phoneNumber(foodtruckCreateDto.getPhoneNumber())
                .build();
        foodtruckRepository.save(foodtruck);
    };

    /*@Override
    @Transactional
    public List<FoodtruckResDto> foodtrucks(double latitud, double longitude){
        List<FoodtruckResDto> foodtrucksRes =

        return
    };*/

    @Override
    @Transactional
    public void updateFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto){

    };

    /*@Override
    @Transactional
    public Long deleteFoodtruck(Long foodtruckId){
        return
    };*/



}
