package com.alibou.security.service.chua;

import com.alibou.security.Entity.Chua;

import java.util.List;
import java.util.Optional;

public interface ChuaService {
    List<Chua> getAll();

    Chua save(Chua chua);

    Optional<Chua> getById(int id);

    void delete(int id);
}
