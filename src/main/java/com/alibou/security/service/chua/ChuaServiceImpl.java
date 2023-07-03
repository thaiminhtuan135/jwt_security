package com.alibou.security.service.chua;

import com.alibou.security.Entity.Chua;
import com.alibou.security.repo.ChuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChuaServiceImpl implements ChuaService{
    @Autowired
    private ChuaRepository repository;
    @Override
    public List<Chua> getAll() {
        return repository.findAll();
    }

    @Override
    public Chua save(Chua chua) {
        return repository.save(chua);
    }

    @Override
    public Optional<Chua> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
