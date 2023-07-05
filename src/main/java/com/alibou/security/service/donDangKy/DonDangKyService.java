package com.alibou.security.service.donDangKy;


import com.alibou.security.Entity.DonDangKy;

import java.util.List;
import java.util.Optional;

public interface DonDangKyService {
    List<DonDangKy> getAll();

    DonDangKy save(DonDangKy entity);

    Optional<DonDangKy> getById(int id);

    void delete(int id);
}
