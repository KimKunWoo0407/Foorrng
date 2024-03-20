
package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService{

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtrucksRepository foodtrucksRepository;

    @Override
    @Transactional
    public void createFoodtruck(FoodtruckCreateDto foodtruckCreateDto){
        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        Foodtrucks save = foodtrucksRepository.save(foodtrucks);

        Foodtruck foodtruck = Foodtruck.builder()
                .foodtruckId(new FoodtruckId(save.getId()))
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

    }


    ;

    /*@Override
    @Transactional
    public Long deleteFoodtruck(Long foodtruckId){
        return
    };*/

    // 사용자 정보를 통해 푸드트럭  찾기
    @Override
    public Long findFoodtruckByUserId(Long userUid) {
        Foodtrucks foodtrucks = (Foodtrucks) foodtrucksRepository.findByUserUserUid(userUid)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_FOUND));
        return foodtrucks.getId();
    }

}