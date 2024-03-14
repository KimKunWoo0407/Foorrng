package com.d205.foorrng.user.controller;


import com.d205.foorrng.common.TmpResponseDto;
import com.d205.foorrng.jwt.token.TokenDto;
import com.d205.foorrng.user.repository.UserRole;
import com.d205.foorrng.user.service.UserSginService;
import com.d205.foorrng.user.dto.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Getter @Setter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignController {

    private final UserSginService userSginService;

    @PostMapping("/regist/owner")
    public ResponseEntity<TokenDto> login(UserDto userDto) {
        System.out.println(userDto.getUserUid());
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getName());

        TokenDto response = userSginService.sign(userDto, UserRole.valueOf("OWNER"));
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
