package com.recruit.assignment.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {
    USER(Role.USER), ADMIN(Role.ADMIN), GUEST(Role.GUEST);

    public static class Role {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String GUEST = "ROLE_GUEST";
    }

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
