package com.d205.foorrng.user.entity;

import com.d205.foorrng.article.Article;
import com.d205.foorrng.foodtruck.entity.FoodtruckLike;
import com.d205.foorrng.requestDelete.RequestDelete;
import com.d205.foorrng.user.repository.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long id;

    private Long userUid;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Nullable
    private String acceess_token;       // 엑세스 토큰

    @Nullable
    private String refresh_token;       // 리프레시 토큰

    @Nullable
    private String fcm_token;           // fcm 토큰

    @Nullable
    private String business_number;     // 사업자 번호

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<FavoriteFood> favoriteFoods;

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<FoodtruckLike> foodtruckLikes;

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<RequestDelete> requestDeleteList;

    @Nullable
    @OneToMany(mappedBy = "user")
    private List<Article> articles;



//    // UserDetails
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>(Arrays.asList("ROLE_USER"));
//
//    // 권한 추가
//    public void addRole(String role) {
//        this.roles.add(role);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public String getPassword() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    // 자격 증명 (비밀번호)
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    // 회원 탈퇴 시 ?
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
