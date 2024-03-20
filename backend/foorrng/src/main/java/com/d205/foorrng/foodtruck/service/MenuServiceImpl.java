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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final FoodtrucksRepository foodtrucksRepository;

    @Override
    @Transactional
    public MenuResDto createMenu(Long foodtrucks_seq, MenuRequestDto menuResquestDto, MultipartFile multipartFile) {
        // 어느 푸드트럭에 해당하는 메뉴인지 찾기
        Foodtrucks foodtruck = foodtrucksRepository.findById(foodtrucks_seq)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_FOUND));

        // 메뉴 저장하기
        Menu menu = Menu.builder()
                .name(menuResquestDto.getName())
                .price(menuResquestDto.getPrice())
//                .picture("Amazon S3 image save Util 로 이미지 저장 후 반환되는 (String) directory 경로"))
                .foodtrucks(foodtruck)
                .build();
        menuRepository.save(menu);

        return MenuResDto.fromEntity(menu);
    }

    // 모든 메뉴 조회하기
    @Override
    public List<MenuResDto> findAllMenus() {
        return menuRepository.findAll()
                .stream().map(MenuResDto::fromEntity).toList();
    }

    // go
    @Override
    public List<MenuResDto> getMenus(Long foodtrucks_seq) {
        Foodtrucks foodtruck = foodtrucksRepository.findById(foodtrucks_seq)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_FOUND));

        // 해당 푸드트럭의 모든 메뉴 가져오기
        List<Menu> menus = menuRepository.findAllByFoodtrucks_Id(foodtruck.getId());

        // Menu 엔티티 리스트를 MenuResDto 리스트로 변환
        return menus.stream()
                .map(menu -> new MenuResDto(
                        menu.getId(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getPicture(),
                        menu.getFoodtrucks().getId()))
                .collect(Collectors.toList());
    }




    @Override
    public void updateMenu(Long foodtrucks_seq, Long menu_seq, MenuResDto menuResDto) {

    }

    @Override
    public Long deleteMenu(Long foodtrucks_seq, Long menu_seq) {
        return null;
    }


}
