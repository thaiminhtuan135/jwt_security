package com.alibou.security.service;

import com.alibou.security.Entity.User;
import com.alibou.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.existsByEmail(email,null);
    }

    @Override
    public boolean existEmail(String email,Integer id) {
        return userRepository.existsByEmail(email,id).isPresent();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
