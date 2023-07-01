package com.alibou.security.repo;

import java.util.List;
import java.util.Optional;

import com.alibou.security.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND (u.id != :id OR :id IS NULL)")
    Optional<User> existsByEmail(@Param("email") String email, @Param("id") Integer id);

    @Query("SELECT u FROM User u " +
            "WHERE (u.PhapDanh = :phapDanh OR :phapDanh IS NULL) " +
            "AND (u.Ten = :ten OR :ten IS NULL) " +
            "AND (u.DaHoanTuc = :daHoanTuc OR :daHoanTuc IS NULL) " +
            "AND (u.GioiTinh = :gioiTinh OR :gioiTinh IS NULL)")
    List<User> pagination(String phapDanh, String ten , Boolean daHoanTuc,String gioiTinh);


//  Optional<User> findByEmail(String email);

//  @Query(value = "SELECT * FROM _user WHERE id = ?1", nativeQuery = true)
//  public User getUserById(Integer id);
}
