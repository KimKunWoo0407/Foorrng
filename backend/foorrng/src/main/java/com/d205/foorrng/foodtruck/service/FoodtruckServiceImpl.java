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
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public FoodtruckResDto createFoodtruck(FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException {
        System.out.println("service");


        // ALL 푸드트럭 entity 생성
        Foodtrucks foodtrucks = Foodtrucks.builder()
                .foodtruckRole(FoodtruckRole.Foodtruck)
                .build();
        Foodtrucks newFoodtrucks = foodtrucksRepository.save(foodtrucks);

        System.out.println("foodtrucks builder");
        System.out.println(newFoodtrucks.getId());

        Long createdDay = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // new랑 builder 차이가 뭐지??? new 값이 있으면 넣어줌. 없으면 안넣음. 이런 건가봐. 값이 있으면
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

        System.out.println("foodtruck builder");
        System.out.println(foodtruck.getFoodtruckId());

        String imgUrl = "";
        if(picture != null) {
            String imgName = "foodtruckIMG/" + foodtruckCreateDto.getName() + "/" + foodtrucks.getId() + ".png"; // 확장명
            String dir = "/foodtruckIMG";
            imgUrl = ImageUtil.saveImageS3(imgName, dir, picture);
            System.out.println("imgurl : " + imgUrl);
        }
        foodtruck.setPicture(imgUrl);
        foodtruckRepository.save(foodtruck);

        System.out.println("foodtruck repository save");
        System.out.println(imgUrl);

        FoodtruckResDto foodtruckResDto = new FoodtruckResDto(foodtruck, newFoodtrucks.getId(), createdDay);

        System.out.println("foodtruck response dto");
        System.out.println(foodtruckResDto);

        return foodtruckResDto;
    };

    @Override
    @Transactional
    public void updateFoodtruck(Long foodtruckId, FoodtruckCreateDto foodtruckCreateDto){

    };


}
