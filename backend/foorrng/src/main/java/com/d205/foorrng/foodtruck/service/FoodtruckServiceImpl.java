package com.d205.foorrng.foodtruck.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.entity.FoodtruckRole;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService{

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3Client amazonS3Client;


    @Override
    @Transactional
    public FoodtruckResDto createFoodtruck(FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException {

        // ALL 푸드트럭 entity 생성
        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        Foodtrucks newFoodtrucks = foodtrucksRepository.save(foodtrucks);

        // 생성날짜 long 타입
        Long createdDay = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // 점주 푸드트럭 entity 생성
        Foodtruck foodtruck = Foodtruck.builder()
                .foodtruckId(new FoodtruckId(newFoodtrucks.getId()))
                .name(foodtruckCreateDto.getName())
                .announcement(foodtruckCreateDto.getAnnouncement())
                .accountInfo(foodtruckCreateDto.getAccountInfo())
                .carNumber(foodtruckCreateDto.getCarNumber())
                .phoneNumber(foodtruckCreateDto.getPhoneNumber())
                .createdDay(createdDay)
                .build();

        // 이미지 s3 저장
        String imgUrl = "";
        if(picture != null) {
            String imgName = "foodtruckIMG/" + foodtruckCreateDto.getName() + "/" + foodtrucks.getId() + ".png"; // 확장명
            String dir = "/foodtruckIMG";
            imgUrl = saveImageS3(picture, imgName, dir);
        }
        foodtruck.setPicture(imgUrl);
        foodtruckRepository.save(foodtruck);

        FoodtruckResDto foodtruckResDto = new FoodtruckResDto(foodtruck, newFoodtrucks.getId(), createdDay);

        return foodtruckResDto;
    };

    @Override
    @Transactional
    public String saveImageS3(MultipartFile pictrue, String imgName, String dir) throws IOException {
        String imgUrl = "";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(pictrue.getSize());

        try {
            amazonS3Client.putObject(bucket+dir, imgName, pictrue.getInputStream(), objectMetadata);
            imgUrl = amazonS3Client.getUrl(bucket+dir, imgName).toString();
            return imgUrl;
        }catch (Exception e){
            e.printStackTrace();
            throw new IOException("이미지 업로드 실패", e);
        }
    };

    @Override
    @Transactional
    public FoodtruckResDto updateFoodtruck(FoodtruckUpdateDto foodtruckUpdateDto, MultipartFile picture) throws IOException{

        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckUpdateDto.getId())
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        Foodtruck foodtruck = foodtruckRepository.findById(foodtruckUpdateDto.getId())
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));

        //수정할라면 수정값 확인하고, 그걸로 넣어줘야함
        // foodtruck 다른 값만 찾아서 값 변경 save
        if (foodtruck.getAnnouncement() != foodtruckUpdateDto.getAnnouncement()) {
            foodtruck.setAnnouncement(foodtruckUpdateDto.getAnnouncement());
        }
        if (foodtruck.getName() != foodtruckUpdateDto.getName()) {
            foodtruck.setName(foodtruckUpdateDto.getName());
        }
        if (foodtruck.getAccountInfo() != foodtruckUpdateDto.getAccountInfo()) {
            foodtruck.setAccountInfo(foodtruckUpdateDto.getAccountInfo());
        }
        if (foodtruck.getCarNumber() != foodtruckUpdateDto.getCarNumber()) {
            foodtruck.setCarNumber(foodtruckUpdateDto.getCarNumber());
        }
        if (foodtruck.getPhoneNumber() != foodtruckUpdateDto.getPhoneNumber()) {
            foodtruck.setPhoneNumber(foodtruckUpdateDto.getPhoneNumber());
        }
        // 추가 : 음식 카테고리 변경
        foodtruckRepository.save(foodtruck);


        // foodtruck res dto 생성 반환
        FoodtruckResDto foodtruckResDto = new FoodtruckResDto(foodtruck, foodtruckUpdateDto.getId());
        return foodtruckResDto;
    };


}
