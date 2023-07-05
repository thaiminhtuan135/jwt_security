package com.alibou.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class DonDangKy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int trangThaiDon;
    private LocalDateTime ngayGuiDon;
    private LocalDateTime ngaySuLy;
    private Integer nguoiSuLyId;
    @Column(name = "dao_trang_id", insertable = false, updatable = false)
    private int daoTrangid;

    @Column(name = "phat_tu_id", insertable = false, updatable = false)
    private int phatTuid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dao_trang_id")
    @JsonBackReference
    private DaoTrang daoTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phat_tu_id")
    @JsonBackReference
    private User user;
}
