package com.alibou.security.repo;

import java.util.List;
import java.util.Optional;

import com.alibou.security.DTO.ThongKePhatTuDTO;
import com.alibou.security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND (u.id != :id OR :id IS NULL)")
    Optional<User> existsByEmail(@Param("email") String email, @Param("id") Integer id);

    @Query("SELECT u FROM User u " +
            "WHERE (:phapDanh IS NULL OR u.PhapDanh = :phapDanh) " +
            "AND (:ten IS NULL OR u.Ten LIKE %:ten% ) " +
            "AND (:daHoanTuc IS NULL OR u.DaHoanTuc = :daHoanTuc) " +
            "AND (:gioiTinh IS NULL OR u.GioiTinh = :gioiTinh )")
    List<User> pagination(String phapDanh, String ten, Boolean daHoanTuc, String gioiTinh);

    @Query("SELECT new com.alibou.security.DTO.ThongKePhatTuDTO(u.email, COUNT(ptdt.daoTrangId)) " +
            "FROM User u " +
            "JOIN PhatTuDaoTrang ptdt ON u.id = ptdt.phatTuId " +
            "WHERE ptdt.daThamGia = true " +
            "GROUP BY u.email order by COUNT(ptdt.daoTrangId) ASC")
    List<ThongKePhatTuDTO> thongKeSoLanPhatTu();

    @Query("select u from User u " +
            "join PhatTuDaoTrang ptdt on u.id = ptdt.phatTuId " +
            "AND (:daHoanTuc IS NULL OR u.DaHoanTuc = :daHoanTuc)" +
            "AND (:gioiTinh IS NULL OR u.GioiTinh = :gioiTinh ) "+
            "AND (:ten IS NULL OR u.Ten LIKE %:ten%) "+
            "where ptdt.daoTrangId = :idDaoTrang and ptdt.daThamGia = true")
    List<User> GetPhatTuByDaoTrangId(int idDaoTrang, Boolean daHoanTuc, String gioiTinh , String ten);
//  Optional<User> findByEmail(String email);

//  @Query(value = "SELECT * FROM _user WHERE id = ?1", nativeQuery = true)
//  public User getUserById(Integer id);
}
