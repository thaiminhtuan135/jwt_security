package com.alibou.security.repo;

import com.alibou.security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhatTuRepository extends JpaRepository<User, Integer>{
}
