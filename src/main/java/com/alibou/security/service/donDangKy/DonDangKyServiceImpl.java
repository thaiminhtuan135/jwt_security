package com.alibou.security.service.donDangKy;

import com.alibou.security.Entity.DonDangKy;
import com.alibou.security.repo.DonDangKyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonDangKyServiceImpl implements DonDangKyService {
    @Autowired
    private DonDangKyRepository repository;

    @Override
    public List<DonDangKy> getAll() {
        return repository.findAll();
    }

    @Override
    public DonDangKy save(DonDangKy entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<DonDangKy> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
