package com.recruit.assignment.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), GUEST("ROLE_GUEST");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
