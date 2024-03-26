package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.food.repository.FoodRepository;
import com.d205.foorrng.food.service.FoodService;
import com.d205.foorrng.foodtruck.entity.*;
import com.d205.foorrng.foodtruck.repository.*;
import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import com.d205.foorrng.foodtruck.response.FoodtruckRepResDto;
import com.d205.foorrng.foodtruck.response.FoodtruckResDto;
import com.d205.foorrng.foodtruck.response.FoodtrucksResDto;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.repository.MarkRepository;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.d205.foorrng.operationInfo.repository.OperationInfoRepository;
import com.d205.foorrng.review.Review;
import com.d205.foorrng.review.ReviewRepository;
import com.d205.foorrng.review.ReviewService;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodtrucksServiceImlp implements FoodtrucksService{

    private final MarkRepository markRepository;
    private final FoodtrucksRepository foodtrucksRepository;
    private final FoodtruckRepository foodtruckRepository;
    private final FoodtruckReportRepository foodtruckReportRepository;
    private final FoodRepository foodRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final FoodtruckLikeRepository foodtruckLikeRepository;
    private final MenuRepository menuRepository;
    private final OperationInfoRepository operationInfoRepository;
    private final FoodService foodService;
    private final ReviewService reviewService;

    @Override
    @Transactional
    public List<FoodtrucksResDto> foodtrucklist(FoodtrucksReqDto foodtrucksReqDto){
        double latitudeMin = Math.min(foodtrucksReqDto.getLatitudeLeft(), foodtrucksReqDto.getLatitudeRight());
        double latitudeMax = Math.max(foodtrucksReqDto.getLatitudeLeft(), foodtrucksReqDto.getLatitudeRight());
        double longitudeMin = Math.min(foodtrucksReqDto.getLongitudeLeft(), foodtrucksReqDto.getLongitudeRight());
        double longitudeMax = Math.max(foodtrucksReqDto.getLongitudeLeft(), foodtrucksReqDto.getLongitudeRight());
        List<Mark> markList = markRepository.findAllByLatitudeBetweenAndLongitudeBetween(latitudeMin, latitudeMax, longitudeMin, longitudeMax);
        List<FoodtrucksResDto> foodtrucksResDtos = new ArrayList<>();
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println(markList.size());
        System.out.println("+++++++++++++++++++++++++++");
        for(Mark mark:markList){
            // test
            System.out.println("markId " + mark.getId());
            System.out.println("foodtrucksId " + mark.getFoodtrucks().getId());
            // test

            // 푸드트럭정보
            Foodtrucks foodtrucks = mark.getFoodtrucks();

            String foodtruckName;
            String foodtruckPicture;
            if(foodtrucks.getFoodtruckRole().equals(FoodtruckRole.Foodtruck)){
                Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId()))
                        .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
                foodtruckName = foodtruck.getName();
                foodtruckPicture = foodtruck.getPicture();
            }else{
                FoodtruckReport foodtruckReport = foodtruckReportRepository.findByFoodtruckId(new FoodtruckReportId(foodtrucks.getId()))
                        .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
                foodtruckName = foodtruckReport.getName();
                foodtruckPicture = foodtruckReport.getPicture();
            }
            // 음식카테고리
            List<Food> foods = foodRepository.findAllByFoodtrucks(foodtrucks);
            List<String> category = new ArrayList<>();
            for (Food food:foods) {
                category.add(food.getName());
            }
            // 푸드트럭 타입
            String type;
            if (foodtrucks.getFoodtruckRole() == FoodtruckRole.Foodtruck){
                type = "Foodtruck";
            }
            else{
                type = "FoodtruckReport";
            }
            // 리뷰 총 개수
            List<Review> reviews = reviewRepository.findByFoodtrucksId(foodtrucks.getId());
            // 푸드트럭 좋아요 여부
            User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
            Optional<FoodtruckLike> foodtruckLike = foodtruckLikeRepository.findByUserAndFoodtrucks(user, foodtrucks);
            Boolean like;
            if(foodtruckLike.isPresent()){
                like = true;
            }else{
                like = false;
            }
            // 푸드트럭 운영 여부
            Boolean operate = mark.getIsOpen();
            FoodtrucksResDto foodtrucksResDto = new FoodtrucksResDto(foodtrucks.getId(), mark.getId(), mark.getLatitude(), mark.getLongitude(), foodtruckName, foodtruckPicture, type, category, reviews.size(), like, operate);
            foodtrucksResDtos.add(foodtrucksResDto);
        }
        return foodtrucksResDtos;
    };
    @Override
    @Transactional
    public Map<String, Object> foodtruckdetail(Long foodtrucksId, Long markId) throws IOException  {
        Map<String, Object> response = new HashMap<>();

        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtrucksId)
                .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));

        if (foodtrucks.getFoodtruckRole() == FoodtruckRole.Foodtruck){
            // 점주 푸드트럭
            Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId()))
                    .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
            List<String> category = foodService.getFoodtruckFood(foodtrucksId);
            FoodtruckResDto foodtruckResDto = new FoodtruckResDto(foodtruck, foodtrucks.getId(), category);
            response.put("role", "foodtruck");
            response.put("foodtruck", foodtruckResDto);
        }
        else{
            // 제보 푸드트럭
            FoodtruckReport foodtruckReport = foodtruckReportRepository.findByFoodtruckId(new FoodtruckReportId(foodtrucks.getId()))
                    .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
            List<String> category = foodService.getFoodtruckFood(foodtrucksId);
            FoodtruckRepResDto foodtruckRepResDto = new FoodtruckRepResDto(foodtruckReport, foodtrucks.getId(), category);
            response.put("role", "foodtruckReport");
            response.put("foodtruck", foodtruckRepResDto);
        }
        List<Map<String,Object>> reviews = reviewService.getReviewlist(foodtrucksId);
        List<Menu> menus = menuRepository.findAllByFoodtrucks_Id(foodtrucks.getId());
        Mark mark = markRepository.findById(markId).get();
        List<OperationInfo> operationInfoList = operationInfoRepository.findAllByMarkId(markId).get();
        response.put("review", reviews);
        response.put("menus", menus);
        response.put("mark", mark);
        response.put("operation", operationInfoList);
        return response;

    };
    @Override
    @Transactional
    public Map<String, Object> myfoodtruck(Long foodtruckId) throws IOException{
        Map<String, Object> response = new HashMap<>();
        Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtruckId))
                .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<String> category = foodService.getFoodtruckFood(foodtruckId);
        FoodtruckResDto foodtruckResDto = new FoodtruckResDto(foodtruck, foodtruckId, category);
        List<Map<String,Object>> reviews = reviewService.getReviewlist(foodtruckId);
        response.put("foodtruck", foodtruckResDto);
        response.put("review", reviews);
        return response;
    };
    @Override
    @Transactional
    public List<Map<String, Object>> myfoodtruckOper(Long foodtruckId) throws IOException{
        List<Map<String, Object>> response = new ArrayList<>();
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckId)
                .orElseThrow(()->new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Mark> marks = markRepository.findByFoodtrucks(foodtrucks);
        for(Mark mark: marks){
            Map<String, Object> responseOne = new HashMap<>();
            List<OperationInfo> operationInfos = operationInfoRepository.findAllByMarkId(mark.getId()).get();
            responseOne.put("mark", mark);
            response.add(responseOne);
        }
        return response;
    };

}
