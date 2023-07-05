package com.alibou.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ChuaDTO {
    private String tenChua;
    private LocalDateTime ngayThanhLap;
    private String diaChi;
    private int truTriId;
}
