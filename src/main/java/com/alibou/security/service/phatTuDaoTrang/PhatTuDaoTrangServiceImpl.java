package com.alibou.security.service.phatTuDaoTrang;

import com.alibou.security.Entity.PhatTuDaoTrang;
import com.alibou.security.repo.PhatTuDaoTrangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhatTuDaoTrangServiceImpl implements PhatTuDaoTrangService {
    @Autowired
    private PhatTuDaoTrangRepository repository;

    @Override
    public List<PhatTuDaoTrang> getAll() {
        return repository.findAll();
    }

    @Override
    public PhatTuDaoTrang save(PhatTuDaoTrang entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<PhatTuDaoTrang> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PhatTuDaoTrang> getByDaoTangIdAndPhatTuId(int daoTrangId, int PhatTuId) {
        return repository.getByDaoTrangIdAndPhatTuId(daoTrangId, PhatTuId);
    }
}
