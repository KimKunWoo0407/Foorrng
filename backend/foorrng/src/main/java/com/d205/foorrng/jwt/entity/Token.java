package com.d205.foorrng.jwt.entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

// redis 내의 key 중복 방지

@Getter
@RedisHash("token")
@AllArgsConstructor
public class Token {
    @Id
    private String id;

    private String refreshToken;

    private Long expiration;

}
