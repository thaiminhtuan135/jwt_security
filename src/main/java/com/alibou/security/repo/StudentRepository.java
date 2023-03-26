package com.alibou.security.repo;

import com.alibou.security.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.firstname = ?1")
    Optional<Student> findStudentByFirstname(String firstname);
}
