package com.alibou.security.service.user;

import com.alibou.security.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Optional<User> getUser(String email);

    boolean existEmail(String email, Integer id);

    Optional<User> getUserById(int id);

    User save(User user);

    void delete(int id);

    List<User> pagination(String phapDanh , String ten,Boolean daHoanTuc, String gioiTinh);

}
