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
import com.d205.foorrng.foodtruck.request.FoodtruckCreateReqDto;
import com.d205.foorrng.foodtruck.request.FoodtruckUpdateReqDto;
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
    public FoodtruckResDto createFoodtruck( FoodtruckCreateReqDto foodtruckCreateReqDto, MultipartFile picture) throws IOException {
        // ALL 푸드트럭 entity 생성
        Foodtrucks foodtrucks = Foodtrucks.builder().role(FoodtruckRole.Foodtruck).build();
        foodtrucksRepository.save(foodtrucks);

        // 생성날짜 long 타입
        Long createdDay = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // 점주 푸드트럭 entity 생성
        Foodtruck foodtruck = Foodtruck.builder()
                .foodtruckId(new FoodtruckId(foodtrucks.getId()))
                .name(foodtruckCreateReqDto.getName())
                .announcement(foodtruckCreateReqDto.getAnnouncement())
                .accountInfo(foodtruckCreateReqDto.getAccountInfo())
                .carNumber(foodtruckCreateReqDto.getCarNumber())
                .phoneNumber(foodtruckCreateReqDto.getPhoneNumber())
                .createdDay(createdDay)
                .build();

        // 이미지 s3 저장
        String imgUrl = "";
        if(picture != null) {
            String imgName = "foodtruckIMG/" + foodtruckCreateReqDto.getName() + "/" + foodtrucks.getId() + ".png"; // 확장명
            String dir = "/foodtruckIMG";
            imgUrl = saveImageS3(picture, imgName, dir);
        }
        foodtruck.updatePicture(imgUrl);
        foodtruckRepository.save(foodtruck);

        return new FoodtruckResDto(foodtruck, foodtrucks.getId(), createdDay);
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
    public FoodtruckResDto updateFoodtruck(FoodtruckUpdateReqDto foodtruckUpdateReqDto, MultipartFile picture) throws IOException{
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckUpdateReqDto.getFoodtruckId()).get();
        Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId())) // FoodtruckId type
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));

        //수정할라면 수정값 확인하고, 그걸로 넣어줘야함
        // foodtruck 다른 값만 찾아서 값 변경 save
        if (foodtruck.getAnnouncement() != foodtruckUpdateReqDto.getAnnouncement()) {
            foodtruck.updateAnnouncement(foodtruckUpdateReqDto.getAnnouncement());
        }
        if (foodtruck.getName() != foodtruckUpdateReqDto.getName()) {
            foodtruck.updateName(foodtruckUpdateReqDto.getName());
        }
        if (foodtruck.getAccountInfo() != foodtruckUpdateReqDto.getAccountInfo()) {
            foodtruck.updateAccountInfo(foodtruckUpdateReqDto.getAccountInfo());
        }
        if (foodtruck.getCarNumber() != foodtruckUpdateReqDto.getCarNumber()) {
            foodtruck.updateCarNumber(foodtruckUpdateReqDto.getCarNumber());
        }
        if (foodtruck.getPhoneNumber() != foodtruckUpdateReqDto.getPhoneNumber()) {
            foodtruck.updatePhoneNumber(foodtruckUpdateReqDto.getPhoneNumber());
        }
        // 추가 : 음식 카테고리 변경
        foodtruckRepository.save(foodtruck);

        // foodtruck res dto 생성 반환
        return new FoodtruckResDto(foodtruck, foodtruckUpdateReqDto.getFoodtruckId(), foodtruck.getCreatedDay());    };
}
