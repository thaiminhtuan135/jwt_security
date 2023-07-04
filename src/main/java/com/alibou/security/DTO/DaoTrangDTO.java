package com.alibou.security.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DaoTrangDTO {
    private String noiToChuc;
    private int soThanhVienThamGia;
    private LocalDateTime thoiGianToChuc;
    private String noiDung;
    private boolean daKetThuc;
    private int nguoiChuTriId;
}
