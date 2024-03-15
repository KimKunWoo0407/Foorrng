package com.d205.foorrng.user.dto;

import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userUid;

    private String name;

    private String email;

    private String role;

}
