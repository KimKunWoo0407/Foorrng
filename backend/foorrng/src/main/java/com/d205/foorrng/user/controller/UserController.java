package com.d205.foorrng.user.controller;


import com.d205.foorrng.jwt.token.TokenDto;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.user.repository.UserRole;
import com.d205.foorrng.user.service.UserSginService;
import com.d205.foorrng.user.dto.UserDto;
import com.d205.foorrng.util.SecurityUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Getter @Setter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserSginService userSginService;

    @PostMapping("/regist/owner")
    public ResponseEntity<TokenDto> loginOwner(UserDto userDto) {
        // request : { userUid: Long, email: String, name: String}
        TokenDto response = userSginService.sign(userDto, UserRole.valueOf("OWNER"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 현재 database 의 user table 에는 role 이 들어가있기 때문에
    // 임시로 점주용 앱 api 와 소비자용 앱 api 를 각각 작성
    @PostMapping("/regist/user")
    public ResponseEntity<TokenDto> loginUser(UserDto userDto) {
        // request : { userUid: Long, email: String, name: String}
        TokenDto response = userSginService.sign(userDto, UserRole.valueOf("USER"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("")
    public ResponseEntity<Long> searchUserInfo(TokenDto token) {

        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();

        return ResponseEntity.status(HttpStatus.OK).body(user.getUserUid());

    }



    // jwt 토큰 작동 체크를 위한 api
    @PostMapping("/check")
    public String check() {
        return "checked";
    }

}
