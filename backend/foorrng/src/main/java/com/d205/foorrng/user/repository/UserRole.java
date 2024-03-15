package com.d205.foorrng.user.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER", "소비자"),
    OWNER("ROLE_OWNER", "점주");

    private final String key;

    private final String title;
}
