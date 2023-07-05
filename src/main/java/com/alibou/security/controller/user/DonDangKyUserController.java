package com.alibou.security.controller.user;

import com.alibou.security.DTO.DonDangKy.DonDangKyUserDTO;
import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.Entity.DonDangKy;
import com.alibou.security.Entity.User;
import com.alibou.security.service.daoTrang.DaoTrangService;
import com.alibou.security.service.donDangKy.DonDangKyService;
import com.alibou.security.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user/don-dang-ky")
@PreAuthorize("hasRole('USER')")
public class DonDangKyUserController {
    @Autowired
    private DonDangKyService donDangKyService;
    @Autowired
    private DaoTrangService daoTrangService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DonDangKyUserDTO donDangKyDTO) {
        Optional<DaoTrang> daoTrang = daoTrangService.getById(donDangKyDTO.getDaoTrangId());
        if (daoTrang.isEmpty()) {
            return new ResponseEntity<>("Dao trang not found", HttpStatus.NOT_FOUND);
        }
        Optional<User> user = userService.getUserById(donDangKyDTO.getPhatTuId());
        if (user.isEmpty()) {
            return new ResponseEntity<>("Phat tu not found", HttpStatus.NOT_FOUND);
        }

        try {

            DonDangKy donDangKy = new DonDangKy();
            donDangKy.setUser(user.get());
            donDangKy.setPhatTuid(user.get().getId());
            donDangKy.setTrangThaiDon(donDangKyDTO.getTrangThaiDon());
            donDangKy.setNgayGuiDon(LocalDateTime.now());
            donDangKy.setDaoTrang(daoTrang.get());
            donDangKy.setDaoTrangid(daoTrang.get().getId());
            return new ResponseEntity<>(donDangKyService.save(donDangKy), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
