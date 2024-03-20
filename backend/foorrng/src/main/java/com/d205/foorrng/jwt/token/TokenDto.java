package com.d205.foorrng.jwt.token;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String grantType;

    private String accessToken;

    @JsonIgnore
    private String refreshToken;
}