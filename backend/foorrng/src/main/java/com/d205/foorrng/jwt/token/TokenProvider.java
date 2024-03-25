package com.d205.foorrng.jwt.token;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.jwt.entity.Token;
import com.d205.foorrng.jwt.repository.TokenRepository;
import com.d205.foorrng.user.dto.UserDto;
import com.d205.foorrng.util.SecurityUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {


    private final TokenRepository tokenRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;
    private final long tokenValidityInMiliseconds = tokenValidityInSeconds * 1000;
    private Key key;



//    public TokenProvider(
//            @Value("${jwt.secret}") String secret,
//            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
//        this.secret = secret;
//        this.tokenValidityInMiliseconds = tokenValidityInSeconds * 1000;
//    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public TokenDto createToken(Authentication authentication) {
        String  authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date accessTokenValidity = new Date(now + tokenValidityInMiliseconds);
        Date refreshTokenValidity = new Date(now + tokenValidityInMiliseconds * 21);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim("token_type", "access")
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(accessTokenValidity)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .claim("token_type", "refresh")
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(refreshTokenValidity)
                .compact();

//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(authentication.getName(), refreshToken);

        Token token = new Token(authentication.getName(), refreshToken, 40L);


        tokenRepository.save(token);
        String storedRefreshToken = tokenRepository.findById(Long.parseLong(getUserDetailsFromToken(refreshToken).getUsername())).get().getRefreshToken();
        System.out.println(storedRefreshToken.equals(refreshToken));
        return new TokenDto("Bearer", accessToken, refreshToken);
    }


    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "",authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }


    public boolean validateToken(String token, String expectedType) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String tokenType = claims.get("token_type", String.class);

            return expectedType.equals(tokenType);
//            return expectedType.equals(tokenType);
        } catch (SecurityException | MalformedJwtException e) {
            logger.info("잘못된 jwt 서명", e);
        } catch (ExpiredJwtException e) {
            if ("refresh".equals(expectedType)) {
                String tokenType = e.getClaims().get("token_type", String.class);
                return "refresh".equals(tokenType);
            }
            logger.info("만료된 jwt 서명", e);
            throw new Exceptions(ErrorCode.UNEXPECTED_TOKEN);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.error("T-001", "토큰이 만료되었습니다."));
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 jwt 서명", e);
        } catch (IllegalArgumentException e) {
            logger.info("잘못된 jwt 토큰", e);
        }
        throw new Exceptions(ErrorCode.TOKEN_NOT_EXIST);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.error("T-002", "토큰이 존재하지 않습니다."));
    }

    public boolean validateRefreshToken(String refreshToken) {
        String storedRefreshToken = tokenRepository.findById(Long.parseLong(getUserDetailsFromToken(refreshToken).getUsername())).get().getRefreshToken();
        return storedRefreshToken.equals(refreshToken);
    }



    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public UserDetails getUserDetailsFromToken(String token) {
        Claims claims = parseClaims(token);
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new User(claims.getSubject(), "", authorities);
    }

    public boolean validateTokenWithoutExpiration(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // 토큰이 유효하고, 기간이 만료되지 않음
            return false;
        } catch (ExpiredJwtException e) {
            // 토큰 기간 만료
            return true;
        } catch (Exception e) {
            log.info("Invalid or malformed JWT Token", e);
            // 토큰이 유효하지않음
            return false;
        }
    }
}
