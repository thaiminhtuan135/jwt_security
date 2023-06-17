package com.alibou.security.repo;

import java.util.Optional;

import com.alibou.security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.email = :email AND (u.id != :id OR :id IS NULL)")
  Optional<User> existsByEmail(@Param("email") String email, @Param("id") Integer id);

//  Optional<User> findByEmail(String email);

//  @Query(value = "SELECT * FROM _user WHERE id = ?1", nativeQuery = true)
//  public User getUserById(Integer id);
}
