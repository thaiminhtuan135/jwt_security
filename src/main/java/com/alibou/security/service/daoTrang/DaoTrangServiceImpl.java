package com.alibou.security.service.daoTrang;

import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.repo.DaoTrangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DaoTrangServiceImpl implements DaoTrangService {
    @Autowired
    private DaoTrangRepository repository;

    @Override
    public List<DaoTrang> getAll() {
        return repository.findAll();
    }

    @Override
    public DaoTrang save(DaoTrang entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<DaoTrang> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
