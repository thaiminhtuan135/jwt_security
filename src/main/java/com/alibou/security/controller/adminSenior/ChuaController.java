package com.alibou.security.controller.adminSenior;

import com.alibou.security.DTO.ChuaDTO;
import com.alibou.security.DTO.DaoTrangDTO;
import com.alibou.security.Entity.Chua;
import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.Entity.User;
import com.alibou.security.service.chua.ChuaService;
import com.alibou.security.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin-senior/chua")
@PreAuthorize("hasRole('ADMIN_SENIOR')")
public class ChuaController {
    @Autowired
    private ChuaService chuaService;

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>("dasd", HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ChuaDTO chuaDTO
    ) {
        Optional<User> truTri = userService.getUserById(chuaDTO.getTruTriId());
        if (truTri.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return userService.getUserById(chuaDTO.getTruTriId()).map(user -> {
            Chua chua = new Chua();
            chua.setTenChua(chuaDTO.getTenChua());
            chua.setNgayThanhLap(chuaDTO.getNgayThanhLap());
            chua.setDiaChi(chuaDTO.getDiaChi());
            chua.setTruTriId(chuaDTO.getTruTriId());
            chua.setCapNhat(LocalDateTime.now());
            return new ResponseEntity<>(chuaService.save(chua), HttpStatus.CREATED);
        }).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ChuaDTO chuaDTO,
                                    @PathVariable Integer id) {
        return chuaService.getById(id).map(chua -> {

            try {
                Optional<User> user = userService.getUserById(chuaDTO.getTruTriId());
                chua.setTenChua(chuaDTO.getTenChua());
                chua.setNgayThanhLap(chuaDTO.getNgayThanhLap());
                chua.setDiaChi(chuaDTO.getDiaChi());
                chua.setCapNhat(LocalDateTime.now());
                chua.setTruTriId(chuaDTO.getTruTriId());
                return new ResponseEntity<>(chuaService.save(chua), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Tri Tri not found", HttpStatus.NOT_FOUND);
            }
        }).orElseGet(
                () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Chua chua = chuaService.getById(id).get();
            chuaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Chua not found", HttpStatus.NOT_FOUND);
        }
    }

}
