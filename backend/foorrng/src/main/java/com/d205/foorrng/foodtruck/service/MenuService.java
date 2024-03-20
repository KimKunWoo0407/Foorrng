package com.d205.foorrng.foodtruck.service;

import com.d205.foorrng.foodtruck.request.MenuRequestDto;
import com.d205.foorrng.foodtruck.response.MenuResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {

    // 메뉴 생성
    MenuResDto createMenu(Long foodtrucks_seq, MenuRequestDto menuResquestDto, MultipartFile multipartFile);

    // 메뉴 조회(해당 푸드트럭)
    List<MenuResDto> getMenus(Long foodtrucks_seq);

    // 메뉴 수정
    void updateMenu(Long foodtrucks_seq, Long menu_seq, MenuResDto menuResDto);

    // 메뉴 삭제
    Long deleteMenu(Long foodtrucks_seq, Long menu_seq);

    // 모든 메뉴 조회
    List<MenuResDto> findAllMenus();

}
