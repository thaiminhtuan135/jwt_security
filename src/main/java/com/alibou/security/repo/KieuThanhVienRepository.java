package com.alibou.security.repo;

import com.alibou.security.Entity.KieuThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KieuThanhVienRepository extends JpaRepository<KieuThanhVien, Integer>{
}
