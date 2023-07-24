package com.alibou.security.controller.manager;

import com.alibou.security.DTO.DonDangKy.DonDangKyManagerDTO;
import com.alibou.security.DTO.DonDangKy.DonDangKyResponse;
import com.alibou.security.Entity.DaoTrang;
import com.alibou.security.Entity.DonDangKy;
import com.alibou.security.Entity.PhatTuDaoTrang;
import com.alibou.security.Entity.User;
import com.alibou.security.Enum.TrangThaidon;
import com.alibou.security.service.daoTrang.DaoTrangService;
import com.alibou.security.service.donDangKy.DonDangKyService;
import com.alibou.security.service.phatTuDaoTrang.PhatTuDaoTrangService;
import com.alibou.security.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/manager/don-dang-ky")
@PreAuthorize("hasRole('MANAGER')")
public class DonDangKyManagerController {
    @Autowired
    private DonDangKyService donDangKyService;
    @Autowired
    private DaoTrangService daoTrangService;

    @Autowired
    private UserService userService;

    @Autowired
    private PhatTuDaoTrangService phatTuDaoTrangService;

    @GetMapping
    public Page<DonDangKy> pagination(@RequestParam(defaultValue = "0") int pageNo,
                                      @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<DonDangKy> list = donDangKyService.getAll();
        return new PageImpl<>(list, pageable, list.size());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DonDangKyManagerDTO donDangKyManagerDTO, @PathVariable Integer id) {

        return donDangKyService.getById(id).map(donDangKy -> {
            try {
                User user = userService.getUserById(donDangKyManagerDTO.getNguoiSuLyId()).get();
                DaoTrang daoTrang = daoTrangService.getById(donDangKy.getDaoTrang().getId()).get();
                donDangKy.setNgaySuLy(LocalDateTime.now());
                donDangKy.setNguoiSuLyId(donDangKyManagerDTO.getNguoiSuLyId());
                donDangKy.setTrangThaiDon(donDangKyManagerDTO.getTrangThaiDon());

                // phat tu dao trang
                Optional<PhatTuDaoTrang> phatTuDaoTrang1 = phatTuDaoTrangService.getByDaoTangIdAndPhatTuId(donDangKy.getDaoTrang().getId(), donDangKy.getUser().getId());
                if (phatTuDaoTrang1.isPresent()) {
                    phatTuDaoTrang1.get().setDaThamGia(donDangKyManagerDTO.getTrangThaiDon() == Integer.parseInt(TrangThaidon.CHAP_NHAN.getValue()));
                    phatTuDaoTrangService.save(phatTuDaoTrang1.get());
                } else {
                    PhatTuDaoTrang phatTuDaoTrang = new PhatTuDaoTrang();
                    phatTuDaoTrang.setDaoTrang(donDangKy.getDaoTrang());
                    phatTuDaoTrang.setDaoTrangId(donDangKy.getDaoTrang().getId());
                    phatTuDaoTrang.setPhatTuId(donDangKy.getUser().getId());
                    phatTuDaoTrang.setUser(donDangKy.getUser());
                    phatTuDaoTrang.setDaThamGia(donDangKyManagerDTO.getTrangThaiDon() == Integer.parseInt(TrangThaidon.CHAP_NHAN.getValue()));
                    phatTuDaoTrangService.save(phatTuDaoTrang);

                    daoTrang.setSoThanhVienThamGia(daoTrang.getSoThanhVienThamGia()+1);
                    daoTrangService.save(daoTrang);
                }
//
                return new ResponseEntity<>(donDangKyService.save(donDangKy), HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Update Fail", HttpStatus.NOT_FOUND);
            }
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Don dang ky not found"));

    }

    @GetMapping("/getAllDaoTrang")
    public Page<DaoTrang> getAllDaoTRang(@RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<DaoTrang> list = daoTrangService.getAll();
        return new PageImpl<>(list, pageable, list.size());
    }

    @GetMapping("/getDonDangKyByDaoTRangId/{id}")
    public List<DonDangKyResponse> getDonDangKyByDaoTRangId(@PathVariable Integer id) {
        List<DonDangKyResponse> list = new ArrayList<>();
        donDangKyService.getAll()
                .stream()
                .filter(donDangKy -> donDangKy.getDaoTrangid() == id)
                .toList()
                .forEach(donDangKy -> {
                    DonDangKyResponse donDangKyResponse = new DonDangKyResponse();
                    donDangKyResponse.setPhatTuId(donDangKy.getUser().getId());
                    donDangKyResponse.setDonDangKyId(donDangKy.getId());
                    donDangKyResponse.setTrangThaiDon(donDangKy.getTrangThaiDon());
                    donDangKyResponse.setNgayGuiDon(donDangKy.getNgayGuiDon());
                    donDangKyResponse.setEmail(donDangKy.getUser().getEmail());
                    donDangKyResponse.setTen(donDangKy.getUser().getTen());
                    list.add(donDangKyResponse);
                });

        return list;
    }
}
