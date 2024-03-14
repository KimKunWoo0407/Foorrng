package com.d205.foorrng.user.repository;

public enum UserRole {
    User("소비자"),
    Owner("점주");

    private String type;
    UserRole(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
