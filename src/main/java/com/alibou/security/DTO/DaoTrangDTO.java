package com.alibou.security.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DaoTrangDTO {
    private String noiToChuc;
    private LocalDateTime thoiGianToChuc;
    private String noiDung;
    private int daKetThuc;
    private int nguoiChuTriId;
}
