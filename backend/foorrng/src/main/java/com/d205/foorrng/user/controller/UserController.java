package com.d205.foorrng.user.controller;


import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.jwt.token.TokenDto;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.user.repository.UserRole;
import com.d205.foorrng.user.service.UserSginService;
import com.d205.foorrng.user.dto.UserDto;
import com.d205.foorrng.util.SecurityUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@Getter @Setter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserSginService userSginService;

    @PostMapping("/regist/owner")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "로그인에 성공했을 때, 응답코드 200 반환"),
//            @ApiResponse(responseCode = "201", description = "회원가입 임시")}
//    )
//    public ResponseEntity<TokenDto> loginOwner(@RequestBody @Valid UserDto userDto) {
        // request : { userUid: Long, email: String, name: String}
    public ResponseEntity<? extends BaseResponseBody> signUpOwner(@RequestBody @Valid UserDto userDto) {

        Long userUid = userSginService.signUp(userDto, "OWNER");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//        if (response.containsKey(true)) {

//            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, response.get(true)));
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, userUid));
    }

//     현재 database 의 user table 에는 role 이 들어가있기 때문에
//     임시로 점주용 앱 api 와 소비자용 앱 api 를 각각 작성
    @PostMapping("/regist/user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "로그인에 성공했을 때, 응답코드 200 반환"),
//            @ApiResponse(responseCode = "201", description = "회원가입 임시")}
//    )
    // request : { userUid: Long, email: String, name: String}
    public ResponseEntity<? extends BaseResponseBody> signUpUser(@RequestBody @Valid UserDto userDto) {

        Long userUid = userSginService.signUp(userDto, "USER");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//        if (response.containsKey(true)) {

//            return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, response.get(true)));
//        }
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, userUid));
    }


    @PostMapping("/login")
    public ResponseEntity<? extends BaseResponseBody> signIn(@RequestBody @Valid UserDto userDto) {

        TokenDto tokenDto = userSginService.signIn(userDto);

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, tokenDto));

    }

    @GetMapping("")
    public ResponseEntity<? extends BaseResponseBody> searchUserInfo() {

        Optional<User> user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> new Exceptions(ErrorCode.USER_NOT_EXIST))));
        if (user.get().getRole() == UserRole.USER) {


        }

        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(0, user));

    }



    // jwt 토큰 작동 체크를 위한 api
    @PostMapping("/check")
    public String check() {
        return "checked";
    }

}
