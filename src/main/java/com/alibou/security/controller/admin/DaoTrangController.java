package com.alibou.security.controller.admin;

import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.Entity.User;
import com.alibou.security.common.Gson;
import com.alibou.security.service.daoTrang.DaoTrangService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin/dao-trang")
@RequiredArgsConstructor
public class DaoTrangController {
    @Autowired
    private DaoTrangService daoTrangService;
    @Autowired
    private UserService userService;
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
//    @PostMapping()
//    public ResponseEntity<?> create(@RequestBody DaoTrangDTO daoTrangDTO
//    ) {
//        return userService.getUserById(daoTrangDTO.getNguoiChuTriId()).map(user -> {
////            DaoTrang daoTrang1 = gson.fromJson(daoTrang, DaoTrang.class);
//            DaoTrang daoTrang = new DaoTrang();
//            daoTrang.setNoiToChuc(daoTrangDTO.getNoiToChuc());
//            daoTrang.setSoThanhVienThamGia(daoTrangDTO.getSoThanhVienThamGia());
//            daoTrang.setThoiGianToChuc(daoTrangDTO.getThoiGianToChuc());
//            daoTrang.setNoiDung(daoTrangDTO.getNoiDung());
//            daoTrang.setDaKetThuc(daoTrangDTO.isDaKetThuc());
//            daoTrang.setNguoiChuTriId(user.getId());
//            daoTrang.setUser(user);
//            return new ResponseEntity<>(daoTrangService.save(daoTrang), HttpStatus.CREATED);
//        }).orElseGet(
//                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
//        );
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@RequestBody String daoTrangDTO,
//                                    @PathVariable Integer id) {
//        DaoTrang daoTrangUpdate = gson.fromJson(String.valueOf(daoTrangDTO), DaoTrang.class);
//        return daoTrangService.getById(id).map(daoTrang -> {
//
//            try {
//                User user = userService.getUserById(daoTrangUpdate.getNguoiChuTriId()).get();
//                daoTrangUpdate.setNguoiChuTriId(user.getId());
//                daoTrangUpdate.setUser(user);
//                daoTrangUpdate.setId(daoTrang.getId());
//                return new ResponseEntity<>(daoTrangService.save(daoTrangUpdate), HttpStatus.OK);
//            } catch (NoSuchElementException e) {
//                return new ResponseEntity<>("Update Fail", HttpStatus.NOT_FOUND);
//            }
//        }).orElseGet(
//                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
//        );
//    }
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

}
