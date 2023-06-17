package com.alibou.security.controller;

import com.alibou.security.Entity.User;
import com.alibou.security.common.Helper;
import com.alibou.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/phattu")
@RequiredArgsConstructor
public class PhatTuController {
    @Autowired
    private UserService userService;
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> update(
            @RequestParam(required = false) String ho,
            @RequestParam(required = false) String tenDem,
            @RequestParam(required = false) String ten,
            @RequestParam(required = false) String phapDanh,
            @RequestParam(value = "anhChup", required = false) MultipartFile anhChup,
            @RequestParam(required = false) String soDienThoai,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate ngaySinh,
            @RequestParam(required = false) LocalDate ngayXuatGia,
            @RequestParam(required = false, defaultValue = "false") Boolean daHoanTuc,
            @RequestParam(required = false) LocalDate ngayHoanTuc,
            @RequestParam(required = false) String gioiTinh,
            @RequestParam(required = false,defaultValue = "false") boolean isUpdateImg,
            @PathVariable(required = false) Integer id) {

        if (userService.existEmail(email, id)) {
            return new ResponseEntity<>("Email already exists, please enter another email", HttpStatus.NOT_FOUND);
        }

        return userService.getUserById(id).map(user -> {

            try {
                if (isUpdateImg == false) {

                }else {
                    if (user.getAnhChup() == null || user.getAnhChup().equals("")) {
                        if (anhChup != null) {
                            user.setAnhChup(Helper.upload(anhChup, "images/phattu"));
                        }
                    } else {
                        if (anhChup != null) {
                            Helper.delete(user.getAnhChup());
                            user.setAnhChup(Helper.upload(anhChup, "images/phattu"));
                        } else {
                            Helper.delete(user.getAnhChup());
                            user.setAnhChup("");
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            user.setHo(ho);
            user.setTen(ten);
            user.setTenDem(tenDem);
            user.setPhapDanh(phapDanh);
            user.setSoDienThoai(soDienThoai);
            user.setNgaySinh(ngaySinh);
            user.setNgayXuatGia(ngayXuatGia);
            user.setNgayXuatGia(ngayXuatGia);
            user.setDaHoanTuc(daHoanTuc);
            user.setNgayHoanTuc(ngayHoanTuc);
            user.setGioiTinh(gioiTinh);
            user.setNgayCapNhat(LocalDate.now());
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }
}
