package com.alibou.security.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    USER_READ("user:read")
    ;
    @Getter
    private final String permission;
}
