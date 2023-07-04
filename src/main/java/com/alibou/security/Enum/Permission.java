package com.alibou.security.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    MANAGER_READ("manager:read"),
    USER_READ("user:read")
    ;
    @Getter
    private final String permission;
}
