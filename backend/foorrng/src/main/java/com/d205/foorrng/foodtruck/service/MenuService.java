package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;

import java.util.List;

public interface MenuService {

    // 메뉴 생성
    MenuResDto createMenu(Long foodtrucks_seq, MenuRequestDto menuResquestDto);

    // 메뉴 조회
    List<MenuRequestDto> menus(Long foodtrucks_seq);

    // 메뉴 수정
    void updateMenu(Long foodtrucks_seq, Long menu_seq, MenuResDto menuResDto);

    // 메뉴 삭제
    Long deleteMenu(Long foodtrucks_seq, Long menu_seq);


}
