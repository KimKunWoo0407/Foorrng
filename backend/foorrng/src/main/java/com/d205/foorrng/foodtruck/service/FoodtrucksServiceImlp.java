package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.food.Food;
import com.d205.foorrng.food.repository.FoodRepository;
import com.d205.foorrng.foodtruck.entity.*;
import com.d205.foorrng.foodtruck.repository.*;
import com.d205.foorrng.foodtruck.request.FoodtrucksReqDto;
import com.d205.foorrng.foodtruck.response.FoodtrucksResDto;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.repository.MarkRepository;
import com.d205.foorrng.operationInfo.OperationInfo;
import com.d205.foorrng.operationInfo.repository.OperationInfoRepository;
import com.d205.foorrng.review.Review;
import com.d205.foorrng.review.ReviewRepository;
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

    @Override
    @Transactional
    public List<FoodtrucksResDto> foodtrucklist(FoodtrucksReqDto foodtrucksReqDto){
        List<Mark> markList = markRepository.findAllByLatitudeBetweenAndLongitudeBetween(foodtrucksReqDto.getLatitude_left(), foodtrucksReqDto.getLongitude_left(), foodtrucksReqDto.getLatitude_right(), foodtrucksReqDto.getLongitude_right());
        List<FoodtrucksResDto> foodtrucksResDtos = new ArrayList<>(); // 배열 초기화
        for(Mark mark:markList){
            Foodtrucks foodtrucks = mark.getFoodtrucks();
            Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId()))
                    .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
            List<Food> foods = foodRepository.findAllByFoodtrucks(foodtrucks);
            List<String> category = new ArrayList<>();
            for (Food food:foods) {
                category.add(food.getName());
            }
            String type;
            if (foodtrucks.getFoodtruckRole() == FoodtruckRole.Foodtruck){
                type = "Foodtruck";
            }
            else{
                type = "FoodtruckReport";
            }
            List<Review> reviews = reviewRepository.findByFoodtrucksId(foodtrucks.getId());
            User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
            Optional<FoodtruckLike> foodtruckLike = foodtruckLikeRepository.findByUserAndFoodtrucks(user, foodtrucks);
            Boolean like;
            if(foodtruckLike.isPresent()){
                like = true;
            }else{
                like = false;
            }
            Boolean operate = false;

            // ++++++++++++++++++++
            // 수정 필요
            // ++++++++++++++++++++

            FoodtrucksResDto foodtrucksResDto = new FoodtrucksResDto(foodtrucks.getId(), mark.getId(), mark.getLatitude(), mark.getLongitude(), foodtruck.getName(), foodtruck.getPicture(), type, category, reviews.size(), like, operate);
            foodtrucksResDtos.add(foodtrucksResDto);
        }
        return foodtrucksResDtos;
    };
    @Override
    @Transactional
    public Map<String, Object> foodtruckdetail(Long foodtrucksId, Long markId) throws IOException {
        Map<String, Object> response = new HashMap<>();

        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtrucksId)
                .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));

        if (foodtrucks.getFoodtruckRole() == FoodtruckRole.Foodtruck){
            // 점주 푸드트럭
            Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId()))
                    .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
            response.put("role", "foodtruck");
            response.put("foodtruck", foodtruck);
        }
        else{
            // 제보 푸드트럭
            FoodtruckReport foodtruckReport = foodtruckReportRepository.findByFoodtruckId(new FoodtruckReportId(foodtrucks.getId()))
                    .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
            response.put("role", "foodtruckReport");
            response.put("foodtruck", foodtruckReport);
        }
        List<Review> reviews = reviewRepository.findByFoodtrucksId(foodtrucks.getId());
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
    public Map<String, Object> myfoodtruck(Long foodtruckId){
        Map<String, Object> response = new HashMap<>();
        Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtruckId))
                .orElseThrow(()-> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Review> reviews = reviewRepository.findByFoodtrucksId(foodtruckId);
        response.put("foodtruck", foodtruck);
        response.put("review", reviews);
        return response;
    };
    @Override
    @Transactional
    public List<Map<String, Object>> myfoodtruckOper(Long foodtruckId){
        List<Map<String, Object>> response = new ArrayList<>();
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckId)
                .orElseThrow(()->new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));
        List<Mark> marks = markRepository.findByFoodtrucks(foodtrucks);
        for(Mark mark: marks){
            Map<String, Object> responseOne = new HashMap<>();
            List<OperationInfo> operationInfos = operationInfoRepository.findAllByMarkId(mark.getId()).get();
            responseOne.put("mark", mark);
            responseOne.put("operation", operationInfos);
            response.add(responseOne);
        }
        return response;
    };

}
