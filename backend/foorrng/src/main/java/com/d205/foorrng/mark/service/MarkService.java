package com.d205.foorrng.mark.service;


import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtruck;
import com.d205.foorrng.foodtruck.entity.FoodtruckId;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtruckRepository;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.mark.Mark;
import com.d205.foorrng.mark.dto.MarkReqDto;
import com.d205.foorrng.mark.repository.MarkRepository;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter @Setter
@RequiredArgsConstructor
public class MarkService {

    private final UserRepository userRepository;
    private final FoodtrucksRepository foodtrucksRepository;
//    private final FoodtruckRepository foodtruckRepository;
    private final MarkRepository markRepository;

    @Transactional
    public Map<String, Object> createMark(Long foodtruckId, MarkReqDto markReqDto) {

//        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtruckId).get();
//        Foodtruck foodtruck = foodtruckRepository.findByFoodtruckId(new FoodtruckId(foodtrucks.getId())).get();

        Mark mark = Mark.builder()
                .foodtrucks(foodtrucks)
                .longitude(markReqDto.getLongitude())
                .latitude(markReqDto.getLatitude())
                .address(markReqDto.getAddress())
                .build();

        markRepository.save(mark);

        Map<String, Object> response = new HashMap<>();

        response.put("foodtruckId", mark.getFoodtrucks().getId());
        response.put("markId", mark.getId());
        response.put("latitude", mark.getLatitude());
        response.put("longitude", mark.getLongitude());

        return response;
    }
}
