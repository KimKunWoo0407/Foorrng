package com.d205.foorrng.user.service;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.jwt.token.TokenDto;
import com.d205.foorrng.jwt.token.TokenProvider;
import com.d205.foorrng.user.dto.UserDto;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.user.repository.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserSginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    public Map<Boolean, TokenDto> signUp(UserDto userDto, String role) {
//
//        Map<Boolean, TokenDto> response = new HashMap<>();
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUserUid(), "");
//
//        System.out.println("1");
//        if (userRepository.findByUserUid(userDto.getUserUid()).orElse(null) != null) {
//            System.out.println("2");
//            // 로그인
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//            TokenDto tokenDto = tokenProvider.createToken(authentication);
//            System.out.println("3");
//
//            response.put(true, tokenDto);
//            return response;
//
//        }
//        System.out.println("4");
//        User user = User.builder()
//                .userUid(userDto.getUserUid())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .role(UserRole.valueOf(role))
//                .build();
//        Long tmp = userRepository.save(user).getUserUid();
//        System.out.println(tmp);
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//
//        TokenDto tokenDto = tokenProvider.createToken(authentication);
//
//        response.put(false, tokenDto);
//        return response;
//    }

    @Transactional
    public Long signUp(UserDto userDto, String role) {

        if (userRepository.findByUserUid(userDto.getUserUid()).isPresent()) {
            throw new Exceptions(ErrorCode.EMAIL_EXIST);
        }
        User user = User.builder()
                .userUid(userDto.getUserUid())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(UserRole.valueOf(role))
                .build();
        Long userUid = userRepository.save(user).getUserUid();

        return userUid;
    }

    @Transactional
    public TokenDto signIn(UserDto userDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUserUid(), "");

        User user = userRepository.findByUserUid(userDto.getUserUid())
                .orElseThrow(() -> new Exceptions(ErrorCode.USER_NOT_EXIST));

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.createToken(authentication);

        return tokenDto;
    }
}
