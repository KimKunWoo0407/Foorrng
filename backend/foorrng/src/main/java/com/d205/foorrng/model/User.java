package com.d205.foorrng.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long id;

    private Long user_uid;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String acceess_token;   // 엑세스 토큰

    private String refresh_token;   // 리프레시 토큰

    private String fcm_token;       // fcm 토큰

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteFood> favoriteFoods;
}
