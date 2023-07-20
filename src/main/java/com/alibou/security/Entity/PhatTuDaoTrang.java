package com.alibou.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

@Data
@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class PhatTuDaoTrang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean daThamGia;
    @Nullable
    private String lyDoKhongThamGia;

    @Column(name = "dao_trang_id", insertable = false, updatable = false)
    private int daoTrangId;
    @Column(name = "phat_tu_id", insertable = false, updatable = false)
    private int phatTuId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dao_trang_id")
    @JsonBackReference
    private DaoTrang daoTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phat_tu_id")
    @JsonBackReference
    private User user;
}
