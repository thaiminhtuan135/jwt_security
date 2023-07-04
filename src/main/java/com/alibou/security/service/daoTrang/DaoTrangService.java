package com.alibou.security.service.daoTrang;


import com.alibou.security.Entity.DaoTrang;

import java.util.List;
import java.util.Optional;

public interface DaoTrangService {
    List<DaoTrang> getAll();

    DaoTrang save(DaoTrang daoTrang);

    Optional<DaoTrang> getById(int id);

    void delete(int id);
}
