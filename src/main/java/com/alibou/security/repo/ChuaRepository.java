package com.alibou.security.repo;

import com.alibou.security.Entity.Chua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuaRepository extends JpaRepository<Chua, Integer>{
}
