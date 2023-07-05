package com.alibou.security.DTO.DonDangKy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DonDangKyManagerDTO {
    private int nguoiSuLyId;
}
