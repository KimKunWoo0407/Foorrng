package com.d205.foorrng.user.service;

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

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class UserSginService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenDto sign(UserDto userDto, UserRole role) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getName());

        boolean isAlready = userRepository.findByUserUid(userDto.getUserUid()).orElse(null) != null;
        System.out.println("1");
        if (isAlready) {
            System.out.println("2");
            // 로그인 로직
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            TokenDto tokenDto = tokenProvider.createToken(authentication);
            System.out.println("3");

            return tokenDto;

        }
        System.out.println("4");
        User user = User.builder()
                .userUid(userDto.getUserUid())
                .name(passwordEncoder.encode(userDto.getName()))
                .email(userDto.getEmail())
                .role(role)
                .build();
        System.out.println(userDto.getUserUid());
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getName());
        System.out.println(user.getRole());

        userRepository.save(user);

        System.out.println(user.getId());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.createToken(authentication);

        return tokenDto;
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
