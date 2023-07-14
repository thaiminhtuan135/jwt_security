package com.alibou.security.controller.admin;

import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.Entity.Chua;
import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.Entity.User;
import com.alibou.security.Enum.Role;
import com.alibou.security.Enum.TrangThaiDaoTRang;
import com.alibou.security.Enum.TrangThaidon;
import com.alibou.security.common.Gson;
import com.alibou.security.common.Helper;
import com.alibou.security.service.daoTrang.DaoTrangService;
import com.alibou.security.service.mail.MailService;
import com.alibou.security.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/dao-trang")
@RequiredArgsConstructor
public class DaoTrangController {
    @Autowired
    private DaoTrangService daoTrangService;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    private final com.google.gson.Gson gson = Gson.gson();

    @GetMapping
    public Page<DaoTrang> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                     @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<DaoTrang> list = daoTrangService.getAll();
        return new PageImpl<>(list, pageable, list.size());
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable Integer id) {
//        try {
//            DaoTrang daoTrang = daoTrangService.getById(id).get();
//            return new ResponseEntity<>(daoTrang, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
//
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody DaoTrangDTO daoTrangDTO
    ) {
        return userService.getUserById(daoTrangDTO.getNguoiChuTriId()).map(user -> {
//            DaoTrang daoTrang1 = gson.fromJson(daoTrang, DaoTrang.class);
            DaoTrang daoTrang = new DaoTrang();
            daoTrang.setNoiToChuc(daoTrangDTO.getNoiToChuc());
            daoTrang.setSoThanhVienThamGia(0);
            daoTrang.setThoiGianToChuc(daoTrangDTO.getThoiGianToChuc());
            daoTrang.setNoiDung(daoTrangDTO.getNoiDung());
            daoTrang.setDaKetThuc(daoTrangDTO.getDaKetThuc());
            daoTrang.setNguoiChuTriId(user.getId());
//            if (daoTrangService.save(daoTrang) != null) {
//                List<String> listuserMail = userService.pagination(null, null, null, null)
//                        .stream()
//                        .filter(user1 -> user1.getRole() == Role.USER)
//                        .map(User::getEmail).toList();
//                listuserMail.forEach(System.out::println);
//                listuserMail.forEach(mail ->
//                        mailService.sendMail
//                                (mail,
//                                        "Thông tin về buổi hoạt động đạo tràng",
//                                        "Nơi tổ chức: " + daoTrang.getNoiToChuc() + "\n" +
//                                                "Thời gian tổ chức " + daoTrang.getThoiGianToChuc() + "\n" +
//                                                "Nội dung: " + daoTrang.getNoiDung() + "\n" +
//                                                "Người trụ trì: " + user.getTen()
//                                )
//                );
//            }
            daoTrangService.save(daoTrang);

//            daoTrang.setUser(user);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        }).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DaoTrangDTO daoTrangDTO,
                                    @PathVariable Integer id) {
//        DaoTrang daoTrangUpdate = gson.fromJson(String.valueOf(daoTrangDTO), DaoTrang.class);
        return daoTrangService.getById(id).map(daoTrang -> {

            try {
                User user = userService.getUserById(daoTrangDTO.getNguoiChuTriId()).get();
                daoTrang.setNoiDung(daoTrangDTO.getNoiDung());
                daoTrang.setNoiToChuc(daoTrangDTO.getNoiToChuc());
                daoTrang.setThoiGianToChuc(daoTrangDTO.getThoiGianToChuc());
                daoTrang.setDaKetThuc(daoTrangDTO.getDaKetThuc());
                daoTrang.setNguoiChuTriId(user.getId());
                return new ResponseEntity<>(daoTrangService.save(daoTrang), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Update Fail", HttpStatus.NOT_FOUND);
            }
        }).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        try {
//            DaoTrang daoTrang = daoTrangService.getById(id).get();
//            daoTrangService.delete(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
//        }
//    }
//    @GetMapping("/getAll")
//    public List<DaoTrang> getAll() {
//        List<DaoTrang> list = daoTrangService.getAll();
//        return list;
//    }
    @GetMapping("/get-data/nguoiChuTri")
    public List<Map<String, Object>> getTrangThai() {
        return Helper.processEntityList(
                userService.getAll()
                        .stream()
                        .filter(user -> user.getRole() == Role.ADMIN)
                        .collect(Collectors.toList()),
                User::getTen,
                User::getId
        );
    }

}
