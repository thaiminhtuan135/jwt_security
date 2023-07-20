package com.alibou.security.repo;

import com.alibou.security.Entity.PhatTuDaoTrang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhatTuDaoTrangRepository extends JpaRepository<PhatTuDaoTrang, Integer> {
    @Query("select ptdt from PhatTuDaoTrang ptdt where ptdt.daoTrangId= :dtId and ptdt.phatTuId = :ptId")
    public Optional<PhatTuDaoTrang> getByDaoTrangIdAndPhatTuId(int dtId, int ptId);
}
