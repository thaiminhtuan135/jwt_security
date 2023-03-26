package com.alibou.security.service;

import com.alibou.security.user.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUser(String email);

}
