package com.alibou.security.service.phatTuDaoTrang;


import com.alibou.security.Entity.PhatTuDaoTrang;

import java.util.List;
import java.util.Optional;

public interface PhatTuDaoTrangService {
    List<PhatTuDaoTrang> getAll();

    PhatTuDaoTrang save(PhatTuDaoTrang entity);

    Optional<PhatTuDaoTrang> getById(int id);

    void delete(int id);

    Optional<PhatTuDaoTrang> getByDaoTangIdAndPhatTuId(int daoTrangId, int PhatTuId);
}
