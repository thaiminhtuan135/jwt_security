package com.alibou.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class DaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String noiToChuc;
    private int soThanhVienThamGia;
    private String ten;
    private LocalDateTime thoiGianToChuc;
    private String noiDung;
    private int daKetThuc;
//    @Column(name = "nguoi_chu_tri_id", insertable = false, updatable = false)
    @Column(name = "nguoi_chu_tri_id")
    private int nguoiChuTriId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "nguoi_chu_tri_id")
//    @JsonBackReference
//    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daoTrang")
    @JsonManagedReference
    private List<PhatTuDaoTrang> phatTuDaoTrangList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "daoTrang")
    @JsonManagedReference
    private List<DonDangKy> donDangKyList;
}
