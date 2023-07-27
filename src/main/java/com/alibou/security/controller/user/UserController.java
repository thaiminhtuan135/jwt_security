package com.alibou.security.controller.user;

import com.alibou.security.Entity.Chua;
import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.Entity.User;
import com.alibou.security.common.Helper;
import com.alibou.security.service.OtpService;
import com.alibou.security.service.chua.ChuaService;
import com.alibou.security.service.kieuThanhVien.KieuThanhVienService;
import com.alibou.security.service.user.UserService;
import com.alibou.security.service.mail.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private KieuThanhVienService kieuThanhVienService;
    @Autowired
    private ChuaService chuaService;

    @GetMapping
    public Page<User> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 @RequestParam(required = false) String phapDanh,
                                 @RequestParam(required = false) String ten,
                                 @RequestParam(required = false) Boolean daHoanTuc,
                                 @RequestParam(required = false) String gioiTinh
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<User> list = userService.pagination(phapDanh, ten, daHoanTuc, gioiTinh);
        return new PageImpl<>(list, pageable, list.size());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> update(
            @RequestParam(required = false, defaultValue = "") String ho,
            @RequestParam(required = false, defaultValue = "") String tenDem,
            @RequestParam(required = false, defaultValue = "") String ten,
            @RequestParam(required = false, defaultValue = "") String phapDanh,
            @RequestParam(required = false, value = "anhChup") MultipartFile anhChup,
            @RequestParam(required = false, defaultValue = "") String soDienThoai,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) LocalDate ngaySinh,
            @RequestParam(required = false) LocalDate ngayXuatGia,
            @RequestParam(required = false, defaultValue = "false") Boolean daHoanTuc,
            @RequestParam(required = false) LocalDate ngayHoanTuc,
            @RequestParam(required = false) String gioiTinh,
            @RequestParam(required = false, defaultValue = "false") boolean isUpdateImg,
            @RequestParam(required = false) int kieuThanhVien,
            @RequestParam(required = false) int chuaId,
            @PathVariable(required = false) Integer id) {

        if (userService.existEmail(email, id)) {
            return new ResponseEntity<>("Email already exists, please enter another email", HttpStatus.NOT_FOUND);
        }

        return userService.getUserById(id).map(user -> {

            try {
                KieuThanhVien ktv = kieuThanhVienService.getById(kieuThanhVien).get();
                Chua chua = chuaService.getById(chuaId).get();
                // img
                try {
                    if (isUpdateImg == false) {

                    } else {
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
                user.setKieuThanhVien(ktv);
                user.setKieu_thanh_vien_id(ktv.getId());
                user.setChua(chua);
                user.setChuaId(chua.getId());
                return new ResponseEntity<>(userService.save(user), HttpStatus.OK);

            } catch (NoSuchElementException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id).get();
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/send-otp-email")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody String password) {
        try {
            User user = userService.getUserById(id).get();

            String otp = otpService.generateOTP();
            boolean checkSendMail = mailService.sendMail("thuctaplts@gmail.com", "this is subject", "This is OTP code: " + otp);

            if (checkSendMail) {
                user.setOtp(otp);
                user.setOtpCreateTime(LocalDateTime.now());
                userService.save(user);
            }

            return new ResponseEntity<>("OPT sent to email", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<?> verifyOtp(@PathVariable Integer id, @RequestParam String otp,
                                       @RequestParam String password) {
        try {
            User user = userService.getUserById(id).get();

            if (!otpService.verifyOTP(otp, user.getOtp(), user.getOtpCreateTime())) {
                return new ResponseEntity<>("OTP expired", HttpStatus.BAD_REQUEST);
            }
            user.setPassword(passwordEncoder.encode(password));
            userService.save(user);
            return new ResponseEntity<>("Change password successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-data/kieu-thanh-vien")
    public List<Map<String, Object>> getKieuThanhVien() {
        return Helper
                .processEntityList(kieuThanhVienService.getAll(), KieuThanhVien::getTenKieu, KieuThanhVien::getId);
    }

    @GetMapping("/get-data/chua")
    public List<Map<String, Object>> getChua() {
        return Helper
                .processEntityList(chuaService.getAll(), Chua::getTenChua, Chua::getId);
    }

    @GetMapping("/getPhatTuByDaoTrangId/{daoTrangId}")
    public Page<User> getPhatTuByDaoTrangId(@RequestParam(defaultValue = "0") int pageNo,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam(required = false) String ten,
                                            @RequestParam(required = false) Boolean daHoanTuc,
                                            @RequestParam(required = false) String gioiTinh,
                                            @PathVariable Integer daoTrangId
    )
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<User> list = userService.getPhatTuByDaoTrangId(daoTrangId,daHoanTuc,gioiTinh,ten);
        return new PageImpl<>(list, pageable, list.size());
    }
}
