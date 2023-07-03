package com.alibou.security.service.kieuThanhVien;

import com.alibou.security.Entity.KieuThanhVien;

import java.util.List;
import java.util.Optional;

public interface KieuThanhVienService {
    List<KieuThanhVien> getAll();
    KieuThanhVien save(KieuThanhVien kieuThanhVien);
    Optional<KieuThanhVien> getById(int id);
    void delete(int id);
}
