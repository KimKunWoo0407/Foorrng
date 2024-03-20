package com.d205.foorrng.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private long userUid;

    @NotNull
    private String name;

    @NotNull
    private String email;

}
