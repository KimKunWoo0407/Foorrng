package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.entity.Menu;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.foodtruck.repository.MenuRepository;
import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final FoodtrucksRepository foodtrucksRepository;

    @Override
    public MenuResDto createMenu(Long foodtrucks_seq, MenuRequestDto menuResquestDto) {
        // 어느 푸드트럭에 해당하는 메뉴인지 찾기
        Foodtrucks foodtruck = foodtrucksRepository.findById(foodtrucks_seq)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_FOUND));

        // 메뉴 저장하기
        Menu menu = Menu.builder()
                .name(menuResquestDto.getName())
                .price(menuResquestDto.getPrice())
                .picture(menuResquestDto.getPicture())
                .foodtrucks(foodtruck)
                .build();
        menuRepository.save(menu);

        return MenuResDto.fromEntity(menu);
    }


    @Override
    public List<MenuRequestDto> menus(Long foodtrucks_seq) {
        return null;
    }

    @Override
    public void updateMenu(Long foodtrucks_seq, Long menu_seq, MenuResDto menuResDto) {

    }

    @Override
    public Long deleteMenu(Long foodtrucks_seq, Long menu_seq) {
        return null;
    }


}
