package com.d205.foorrng.requestDelete;

import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.user.User;
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
public class RequestDelete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_delete")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="foodtrucks_seq")
    private Foodtrucks foodtrucks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;
}
