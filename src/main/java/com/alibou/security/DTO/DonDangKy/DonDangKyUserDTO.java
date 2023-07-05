package com.alibou.security.DTO.DonDangKy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DonDangKyUserDTO {
    private int phatTuId;
    private int trangThaiDon;
    private int daoTrangId;
}
