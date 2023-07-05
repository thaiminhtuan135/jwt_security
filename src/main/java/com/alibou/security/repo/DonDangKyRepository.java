package com.alibou.security.repo;

import com.alibou.security.Entity.DonDangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonDangKyRepository extends JpaRepository<DonDangKy, Integer> {
}
