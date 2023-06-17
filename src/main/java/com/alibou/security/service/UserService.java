package com.alibou.security.service;

import com.alibou.security.Entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUser(String email);

    boolean existEmail(String email, Integer id);

    Optional<User> getUserById(int id);

    User save(User user);
}
