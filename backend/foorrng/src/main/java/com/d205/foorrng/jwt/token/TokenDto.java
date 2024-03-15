package com.d205.foorrng.jwt.token;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String grantType;

    private String accessToken;

    private String refreshToken;
}
