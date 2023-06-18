package com.alibou.security.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.alibou.security.Enum.Permission.ADMIN_READ;
import static com.alibou.security.Enum.Permission.USER_READ;

@RequiredArgsConstructor
public enum Role {

  ADMIN(
          Set.of(
                  ADMIN_READ,
                  USER_READ
          )
  ),
  USER(
          Set.of(
                  USER_READ
          )
  )
  ;
  @Getter
  private final Set<Permission> permissions;
  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = new java.util.ArrayList<>(getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.name()))
            .toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
    return authorities;
  }
}
