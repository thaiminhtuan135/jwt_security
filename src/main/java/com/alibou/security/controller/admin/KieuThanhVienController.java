package com.alibou.security.controller.admin;

import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.Entity.User;
import com.alibou.security.service.kieuThanhVien.KieuThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/kieu-thanh-vien")
@PreAuthorize("hasRole('ADMIN')")
public class KieuThanhVienController {
    @Autowired
    private KieuThanhVienService kieuThanhVienService;

    @GetMapping
    public Page<KieuThanhVien> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<KieuThanhVien> list = kieuThanhVienService.getAll();
        return new PageImpl<>(list, pageable, list.size());
    }

}
