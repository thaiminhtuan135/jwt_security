package com.alibou.security.controller.admin;

import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.Entity.User;
import com.alibou.security.common.Gson;
import com.alibou.security.common.Helper;
import com.alibou.security.service.kieuThanhVien.KieuThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/kieu-thanh-vien")
@PreAuthorize("hasRole('ADMIN')")
public class KieuThanhVienController {
    @Autowired
    private KieuThanhVienService kieuThanhVienService;
    private final com.google.gson.Gson gson = Gson.gson();

    @GetMapping
    public Page<KieuThanhVien> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<KieuThanhVien> list = kieuThanhVienService.getAll();
        return new PageImpl<>(list, pageable, list.size());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            KieuThanhVien kieuThanhVien = kieuThanhVienService.getById(id).get();
            return new ResponseEntity<>(kieuThanhVien, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Không tìm thấy kiểu thành viên", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody String dataUpdate) {
//        Gson gson = new Gson();
        return kieuThanhVienService.getById(id).map(kieuThanhVien -> {
            KieuThanhVien kieuThanhVien1 = gson.fromJson(dataUpdate, KieuThanhVien.class);
            kieuThanhVien1.setId(kieuThanhVien.getId());
            return new ResponseEntity<>(kieuThanhVienService.save(kieuThanhVien1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            KieuThanhVien kieuThanhVien = kieuThanhVienService.getById(id).get();
            kieuThanhVienService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Không tìm thấy kiểu thành viên", HttpStatus.NOT_FOUND);
        }
    }
}
