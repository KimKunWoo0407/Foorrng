package com.d205.foorrng.foodtruck.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckImgRepository;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.util.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService{

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @Override
    @Transactional
    public Long createFoodtruck(FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException {
        // ALL 푸드트럭 entity 생성
        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        Foodtrucks newFoodtrucks = foodtrucksRepository.save(foodtrucks);

        // 점주 푸드트럭 entity 생성
        Foodtruck foodtruck = Foodtruck.builder()
                .foodtruckId(new FoodtruckId(newFoodtrucks.getId()))
                .name(foodtruckCreateDto.getName())
                .announcement(foodtruckCreateDto.getAnnouncement())
                .accountInfo(foodtruckCreateDto.getAccountInfo())
                .carNumber(foodtruckCreateDto.getCarNumber())
                .phoneNumber(foodtruckCreateDto.getPhoneNumber())
                .build();

        String imgUrl = "";
        if(picture != null) {
            imgUrl = ImageUtil.saveImageS3(foodtrucks, foodtruckCreateDto, picture);
        }
        foodtruck.setPicture(imgUrl);
        foodtruckRepository.save(foodtruck);

        return newFoodtrucks.getId();
    };

    @Override
    @Transactional
    public void updateFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto){

    };


}
