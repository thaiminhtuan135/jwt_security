package com.alibou.security.repo;

import com.alibou.security.Entity.DaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoTrangRepository extends JpaRepository<DaoTrang, Integer>{
}
