package com.alibou.security.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

//  @Query(value = "SELECT * FROM _user WHERE id = ?1", nativeQuery = true)
//  public User getUserById(Integer id);

}
