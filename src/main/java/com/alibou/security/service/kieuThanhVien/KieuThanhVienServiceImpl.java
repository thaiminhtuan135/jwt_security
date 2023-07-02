package com.alibou.security.service.kieuThanhVien;

import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.repo.KieuThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KieuThanhVienServiceImpl implements KieuThanhVienService {
    @Autowired
    private KieuThanhVienRepository repository;
    @Override
    public List<KieuThanhVien> getAll() {
        return repository.findAll();
    }
    @Override
    public KieuThanhVien save(KieuThanhVien kieuThanhVien) {
        return repository.save(kieuThanhVien);
    }

    @Override
    public Optional<KieuThanhVien> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
