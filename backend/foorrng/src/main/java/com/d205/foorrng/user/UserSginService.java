package com.d205.foorrng.user;

import com.d205.foorrng.jwt.token.JwtFilter;
import com.d205.foorrng.jwt.token.TokenDto;
import com.d205.foorrng.jwt.token.TokenProvider;
import com.d205.foorrng.user.dto.UserDto;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.user.repository.UserRole;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserSginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public String sign(UserDto userDto) {
        if (userRepository.findByUserUid(userDto.getUserUid()).orElse(null) != null) {
            // 로그인 로직
            User user = userRepository.findByUserUid(userDto.getUserUid()).get();
//            throw signIn(user.getUserUid(), user.getName());
            // 임시 -> 토큰 발급으로 대체
            return user.getUserUid().toString();

        }

        User user = User.builder()
                .userUid(userDto.getUserUid())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(UserRole.valueOf(userDto.getRole()))
                .build();

        userRepository.save(user);
        // 임시
        return "회원가입 완료";
    }


//    @Transactional
//    public ResponseEntity<TokenDto> signIn(Long userUid, String name) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userUid, name);
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.createToken(authentication);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//
//
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
//    }


}
