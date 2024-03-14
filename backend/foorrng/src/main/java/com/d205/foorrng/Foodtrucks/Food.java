package com.d205.foorrng.Foodtrucks;

import com.d205.foorrng.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_seq")
    private Long id;

    private String name;      // 음식 메뉴 이름

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foodtrucks_seq")
    private Foodtrucks foodtrucks;
}
