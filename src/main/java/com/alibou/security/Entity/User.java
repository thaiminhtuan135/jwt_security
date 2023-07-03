package com.alibou.security.Entity;

import com.alibou.security.Enum.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String Ho;
  private String TenDem;
  private String Ten;
  private String PhapDanh;
  private String AnhChup;
  private String SoDienThoai;
  private String email;
  private LocalDate NgaySinh;
  private LocalDate NgayXuatGia;
  private boolean DaHoanTuc;
  private LocalDate NgayHoanTuc;
  private String GioiTinh;
  private LocalDate NgayCapNhat;
  private String password;
  @Nullable
  private String otp;
  @Nullable
  private LocalDateTime otpCreateTime;
  @Nullable
  private LocalDateTime createdAt;
  @Nullable
  private LocalDateTime updatedAt;
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "kieu_thanh_vien_id", insertable = false, updatable = false)
  private int kieu_thanh_vien_id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
  @JsonManagedReference
  private List<Token> tokens;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<DaoTrang> daoTrangList;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<PhatTuDaoTrang> phatTuDaoTrangList;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<DonDangKy> donDangKyList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kieu_thanh_vien_id")
  @JsonBackReference
  private KieuThanhVien kieuThanhVien;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
