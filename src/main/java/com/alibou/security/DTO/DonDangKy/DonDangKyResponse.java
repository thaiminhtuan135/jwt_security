package com.alibou.security.DTO.DonDangKy;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class DonDangKyResponse {
    private int phatTuId;
    private int donDangKyId;
    private int trangThaiDon;
    private LocalDateTime ngayGuiDon;
    private String email;
    private String ten;
}
